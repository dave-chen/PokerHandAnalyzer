package com.example.poker.service;

import com.example.poker.domain.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Eval implements PokerService {

    /**
     * Method to check if a given list of 7 cards has a straight.
     * A straight is a hand that contains five cards of sequential value. An Ace can be considered low (the card below a 2)
     * or high (the card above a King). A wrap around straight, containing both the 2 and King, is not allowed.
     * @param hand
     * @return true if a hand has a straight, false otherwise
     */
    public boolean isStraight(List<Card> hand) {
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
        return false;
    }

}
