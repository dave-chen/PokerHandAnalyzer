package com.example.poker.domain;

/* the Suit enum, each suit is represented by a single character,
which is stored in the symbol field.
The enum also provides a getSymbol method to return the symbol for a given suit.*/
public enum Suit {
    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C'),
    SPADES('S');

    private char symbol;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
