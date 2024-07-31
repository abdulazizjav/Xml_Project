package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String cardName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardPassword;
    private String userId;
}
