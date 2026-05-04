package org.example;

import org.example.loaninterface.LoanMediator;
import org.example.services.CreditScoreService;
import org.example.services.IncomeCheckService;
import org.example.services.LoanService;
import org.example.services.NotificationService;
import org.example.services.User;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Create colleague instances
        CreditScoreService creditService = new CreditScoreService();
        IncomeCheckService incomeService = new IncomeCheckService();
        NotificationService notifyService = new NotificationService();

        // 2. Create the mediator and inject colleagues (no "new" inside mediator)
        LoanMediator mediator = new LoanService(creditService, incomeService, notifyService);

        // 3. Process loans — mediator is stateless; User is passed per request
        User user1 = new User("Cap", 680, 34000);
        User user2 = new User("Steve", 450, 50000);

        mediator.processLoan(user1);
        System.out.println("---------------- User 2 ----------------");
        mediator.processLoan(user2);
    }
}