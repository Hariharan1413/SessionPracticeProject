package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
          OrderService serviceOrd = new OrderService();
          EmailNotifier email = new EmailNotifier("jj@gmail.com");
          SMSNotifier sms = new SMSNotifier(12345678);
          serviceOrd.addUser(email);
          serviceOrd.addUser(sms);

          serviceOrd.notifyToUsers("Packed");

          serviceOrd.removeUser(email);

          serviceOrd.notifyToUsers("shipped");
    }
}