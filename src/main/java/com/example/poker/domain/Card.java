package com.example.poker.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
//@Schema(example = "9D\", \"8C\",\"2S\", \"3C\", \"4H\", \"5D\", \"6C\"",
//        description = "A hand consists of any 7 cards.  " +
//                "Each card has a suit (Hearts, Clubs, Diamonds or Spades) and a value (Ace, 2-10, Jack, Queen or King).")
public class Card {

    @Schema(hidden = true)
    private int rank;

    @Schema(hidden = true)
    private char suit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card1 = (Card) o;
        return Objects.equals(card, card1.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card);
    }

    private String card;

    public Card(final String card) {
        this.card = card;
        extractCard(card);
    }

    public int getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    private void extractCard(String card) {
        if (this.card == null || this.card.isEmpty() || this.card.length() != 2){
            throw new IllegalArgumentException("Invalid card: " + card);
        }

        this.rank = getRank(this.card.charAt(0));
        this.suit = getSuit(this.card.charAt(1));
    }

    private char getSuit(char suitChar) {
        if(suitChar == 'C' || suitChar == 'D' || suitChar == 'H' || suitChar == 'S') {
           return suitChar;
        }
        throw new IllegalArgumentException("Invalid suit: " + suitChar + " Valid suit: [(H) Hearts, (C) Clubs, (D) Diamonds or (S) Spades]");
    }

    private int getRank(char rankChar) {
        switch (rankChar) {
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 1;
            default:
                throw new IllegalArgumentException("Invalid rank: " + rankChar + " Valid ranks: 2, 3, 4, 5, 6, 7, 8, 9, T, Q, K, A");
        }
    }
}
