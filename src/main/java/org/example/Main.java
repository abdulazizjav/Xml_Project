package org.example;

import model.User;
import model.Card;
import service.UserService;
import service.CardService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scInt = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);
    static UserService userService = new UserService();
    static CardService cardService = new CardService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register   2. Login    0. Exit");
            int command = scInt.nextInt();
            switch (command) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    register();
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String username = scStr.nextLine();
                    System.out.print("Enter password: ");
                    String password = scStr.nextLine();
                    User user = userService.login(username, password);
                    if (user != null) {
                        System.out.println("Login successful!");
                        manageCards(user);
                    } else {
                        System.out.println("Login failed!");
                    }
                }
                default -> System.out.println("Error!");
            }
        }
    }

    private static void register() {
        System.out.print("Enter name: ");
        String name = scStr.nextLine();
        System.out.print("Enter username: ");
        String username = scStr.nextLine();
        System.out.print("Enter password: ");
        String password = scStr.nextLine();
        User user = new User(name, username, password);
        userService.addUser(user);
        System.out.println("User registered successfully!");
    }

    private static void manageCards(User user) {
        int stepCodeCard = 10;
        while (stepCodeCard != 0) {
            System.out.println("1. Add Card     2. Card List   0. Exit");
            stepCodeCard = scInt.nextInt();
            scInt.nextLine();
            switch (stepCodeCard) {
                case 1 -> {
                    System.out.println("Enter the card name: ");
                    String cardName = scStr.nextLine();
                    System.out.println("Enter the card number: ");
                    String cardNumber = scStr.nextLine();
                    System.out.println("Enter the expiry date: ");
                    String cardExpiryDate = scStr.nextLine();
                    System.out.println("Enter the password: ");
                    String cardPassword = scStr.nextLine();
                    Card card = new Card(cardName, cardNumber, cardExpiryDate, cardPassword, user.getUsername());
                    if (cardService.add(card) != null) {
                        System.out.println("Card added successfully");
                    } else {
                        System.out.println("Card already exists");
                    }
                }
                case 2 -> {
                    List<Card> cards = cardService.list();
                    for (Card c : cards) {
                        System.out.println(c);
                    }
                }
                case 0 -> stepCodeCard = 0;
                default -> System.out.println("Error!");
            }
        }
    }
}
