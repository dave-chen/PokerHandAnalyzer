package com.example.poker.service;

import com.example.poker.domain.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Eval implements PokerService {
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
            maxRank = Math.max(maxRank, rank);
        }

        int consecutiveCount = 0;
        for (int i = 1; i <= maxRank; i++) {
            if (ranks[i] == 1) {
                consecutiveCount++;
                if (consecutiveCount == 5) {
                    return true;
                }
            } else {
                consecutiveCount = 0;
            }
        }

        return false;
    }

}
