package com.example.poker.service;

import com.example.poker.domain.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Eval implements PokerService {

    private static final Logger logger = LoggerFactory.getLogger(PokerService.class);

    public static final int SEVEN_CARD = 7;

    /**
     * Method to check if a given list of 7 cards has a straight.
     * A straight is a hand that contains five cards of sequential value. An Ace can be considered low (the card below a 2)
     * or high (the card above a King). A wrap around straight, containing both the 2 and King, is not allowed.
     * @param hand
     * @return true if a hand has a straight, false otherwise
     */
    public boolean isStraight(final List<Card> hand) {

        if (hand == null || hand.isEmpty()){
            logger.error("hand cannot be null or empty");
            throw new IllegalArgumentException("hand cannot be null or empty");
        }

        if (isInvalidHand(hand)){
            logger.error("Invalid hand");
            throw new IllegalArgumentException("Invalid hand");
        }

        int[] ranks = new int[15];
        int maxRank = 0;

        for (Card card : hand) {
            int rank = card.getRank();
            //special case for A
            if (rank == 1) {
                ranks[rank] = 1;
                rank = 14;
            }
            ranks[rank] = 1;
            //get the max value of all the cards
            maxRank = Math.max(maxRank, rank);
            logger.debug("MaxRank: " + rank);
        }

        int consecutiveCount = 0;
        for (int i = 1; i <= maxRank; i++) {
            if (ranks[i] == 1) {
                consecutiveCount++;
                if (consecutiveCount == 5) {
                    //found a straight
                    return true;
                }
            } else {
                consecutiveCount = 0;
            }
        }

        //no straight
        logger.info("No straight found!");
        return false;
    }

    /**
     * Check a 7-card hand is invalid if any of the following conditions are true:
     *
     * The hand contains fewer than 7 cards.
     * The hand contains more than 7 cards.
     * The hand contains duplicate cards.
     * @param cards
     * @return
     */
    public boolean isInvalidHand(List<Card> cards) {
        // Check that the size of the hand is exactly 7
        if (cards.size() != SEVEN_CARD) {
            logger.debug("Invalid card size!");
            return true;
        }

        // Check for duplicate cards
        if (!hasNoDuplicateCards(cards)) {
            return true;
        }

        // If we reach this point, the hand is valid
        return false;
    }

    private boolean hasNoDuplicateCards(List<Card> cards) {
        Set<Card> uniqueCards = new HashSet<>();
        for (Card card : cards) {
            if (uniqueCards.contains(card)) {
                logger.debug("Hand contains duplicate cards!");
                return false;
            }
            uniqueCards.add(card);
        }
        return true;
    }

}
