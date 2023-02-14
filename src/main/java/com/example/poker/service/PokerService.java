package com.example.poker.service;

import com.example.poker.domain.Card;

import java.util.List;

public interface PokerService {
    public boolean isStraight(List<Card> hand);
}
