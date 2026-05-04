package org.example.loaninterface;

import org.example.colleague.Colleague;
import org.example.services.User;

/**
 * Mediator contract — coordinates communication between colleague services.
 * Colleagues never talk to each other directly; they go through the mediator.
 */
public interface LoanMediator {

    /** Register a colleague with the mediator. */
    void registerColleague(Colleague colleague);

    /** Orchestrate the full loan-processing workflow for the given user. */
    void processLoan(User user);
}
