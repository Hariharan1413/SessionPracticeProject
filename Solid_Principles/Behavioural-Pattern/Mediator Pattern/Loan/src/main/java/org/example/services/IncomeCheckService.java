package org.example.services;

import org.example.colleague.Colleague;
import org.example.loaninterface.IncomeCheck;

/**
 * Colleague: verifies that user income meets the minimum requirement.
 */
public class IncomeCheckService extends Colleague implements IncomeCheck {

    private static final double MIN_INCOME = 25000;

    @Override
    public boolean checkUserIncome(double salary) {
        return salary > MIN_INCOME;
    }
}
