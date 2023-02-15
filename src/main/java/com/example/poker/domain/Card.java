package com.example.poker.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@Schema(example = "[\"8H\", \"9C\", \"2S\", \"7C\", \"4H\", \"5D\", \"6C\" ]",
        description = "A hand consists of any 7 cards.  " +
                "Each card has a suit (Hearts, Clubs, Diamonds or Spades) and a value (Ace, 2-10, Jack, Queen or King).")
public class Card {

    @Schema(hidden = true)
    private int rank;

    @Schema(hidden = true)
    private char suit;

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
        this.suit = this.card.charAt(1);
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
                throw new IllegalArgumentException("Invalid rank: " + rankChar);
        }
    }
}
