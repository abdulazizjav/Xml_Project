package service;

import com.fasterxml.jackson.databind.SerializationFeature;
import model.Card;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Cards;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardService {
    private static final String XML_FILE_PATH = "cards.xml";
    private XmlMapper xmlObjectMapper = new XmlMapper();
    private static List<Card> cards = new ArrayList<>();

    public CardService() {
        cards = fileRead(XML_FILE_PATH);
    }

    private boolean hasCard(String cardNumber) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }

    public Card add(Card card) {
        if (hasCard(card.getCardNumber())) {
            return null;
        }
        cards.add(card);
        fileWrite(cards, XML_FILE_PATH);
        return card;
    }

    public Card login(String cardNumber) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    public List<Card> list() {
        return new ArrayList<>(cards);
    }

    private void fileWrite(List<Card> cards, String path) {
        try {
            xmlObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Cards cardsWrapper = new Cards(cards);
            xmlObjectMapper.writeValue(new File(path), cardsWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Card> fileRead(String path) {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            Cards cardsWrapper = xmlObjectMapper.readValue(file, Cards.class);
            return cardsWrapper.getCards();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
