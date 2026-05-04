package org.example.colleague;

import org.example.loaninterface.LoanMediator;

/**
 * Base class for all colleague services.
 * Each colleague holds a reference to the mediator and communicates
 * only through the mediator — never directly with other colleagues.
 */
public abstract class Colleague {

    protected LoanMediator mediator;

    public void setMediator(LoanMediator mediator) {
        this.mediator = mediator;
    }
}

