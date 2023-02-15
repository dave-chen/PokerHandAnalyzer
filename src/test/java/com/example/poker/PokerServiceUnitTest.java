package com.example.poker;

import com.example.poker.domain.Card;
import com.example.poker.service.Eval;
import com.example.poker.service.PokerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerServiceUnitTest {

    PokerService service = new Eval();

    @Test
    public void generateAHand() {
        int N = 1;  // Number of Hands to deal
        int GAME = 7; // game is five card stud so deal 5 cards

        String[] suit = {"C", "D", "H", "S"};
        String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        int SUITS = suit.length;
        int RANKS = rank.length;
        int DECK_SIZE = SUITS * RANKS;

        // initialize deck
        String[] deck = new String[DECK_SIZE];
        for (int i = 0; i < RANKS; i++) {
            for (int j = 0; j < SUITS; j++) {
                deck[SUITS * i + j] = rank[i] + suit[j];
            }
        }

        // shuffle deck, deal a hand
        // repeat N = Number of Hands times
        for (int hands = 0; hands < N; hands++) {

            // shuffle
            for (int i = 0; i < DECK_SIZE; i++) {
                int r = i + (int) (Math.random() * (DECK_SIZE - i));
                String t = deck[r];
                deck[r] = deck[i];
                deck[i] = t;
            }

            // print one five-card hand
            for (int i = 0; i < GAME; i++) {
                System.out.print(deck[i] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test_had_has_no_straight() {
        List<Card> hand2 = Arrays.asList(
                new Card("8C"),
                new Card("9D"),
                new Card("TH"),
                new Card("3S"),
                new Card("QC"),
                new Card("KH"),
                new Card("AS"));

        assertFalse(service.isStraight(hand2));
    }


    //check wrap around Q-K-A-2-3
    @Test
    public void test_for_wrap_around_straight() {
        List<Card> hand2 = Arrays.asList(
                new Card("QC"),
                new Card("KD"),
                new Card("AH"),
                new Card("2S"),
                new Card("3C"),
                new Card("8H"),
                new Card("9S"));

        assertFalse(service.isStraight(hand2));
    }

    @Test
    public void test_hand_has_straight() {
        List<Card> hand1 = Arrays.asList(
                new Card("8C"),
                new Card("9D"),
                new Card("TH"),
                new Card("JS"),
                new Card("QC"),
                new Card("KH"),
                new Card("AS"));
        assertTrue(service.isStraight(hand1));
    }

    @Test
    public void test_hand_has_straight_with_A_high_end() {
        List<Card> hand1 = Arrays.asList(
                new Card("TC"),
                new Card("JD"),
                new Card("QH"),
                new Card("KS"),
                new Card("AC"),
                new Card("4H"),
                new Card("5S"));
        assertTrue(service.isStraight(hand1));
    }

    @Test
    public void test_hand_has_straight_with_A_low_end() {
        List<Card> hand1 = Arrays.asList(
                new Card("AC"),
                new Card("2D"),
                new Card("3H"),
                new Card("4S"),
                new Card("5C"),
                new Card("8H"),
                new Card("9S"));
        assertTrue(service.isStraight(hand1));
    }

    @Test
    public void test_invalid_hand_with_dup() {
        List<Card> hand1 = Arrays.asList(
                new Card("2D"),
                new Card("2D"),
                new Card("3H"),
                new Card("4S"),
                new Card("5C"),
                new Card("8H"),
                new Card("9S"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {service.isStraight(hand1);}, " Invalid hand");
    }

    @Test
    public void test_invalid_hand_morethan_7() {
        List<Card> hand1 = Arrays.asList(
                new Card("AD"),
                new Card("2D"),
                new Card("3H"),
                new Card("4S"),
                new Card("5C"),
                new Card("8H"),
                new Card("9S"),
                new Card("QS"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {service.isStraight(hand1);}, " Invalid hand");
    }

    @Test
    public void test_invalid_hand_with_null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {service.isStraight(null);}, "hand cannot be null or empty");
    }

    @Test
    public void test_invalid_hand_with_empty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {service.isStraight(new ArrayList());}, "hand cannot be null or empty");
    }
}
