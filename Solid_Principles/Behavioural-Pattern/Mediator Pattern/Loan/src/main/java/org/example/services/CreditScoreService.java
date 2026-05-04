package org.example.services;

import org.example.colleague.Colleague;
import org.example.loaninterface.CreditScoreCheck;

/**
 * Colleague: checks whether a user's credit score meets the threshold.
 * Communicates only through the mediator — never calls other services directly.
 */
public class CreditScoreService extends Colleague implements CreditScoreCheck {

    private static final int MIN_CREDIT_SCORE = 600;

    @Override
    public boolean checkScore(int score) {
        return score > MIN_CREDIT_SCORE;
    }
}
