Order Status System (Observer Pattern in Java)
=============================================

What this project is
--------------------
This is a small Java + Maven project that demonstrates the Observer Pattern.

Real-life idea used here:
- An order status changes (for example: Packed -> Shipped).
- Multiple users want updates automatically (email, SMS, admin dashboard).

Instead of hard-coding notifications everywhere, the system keeps a list of listeners
(observers) and notifies all of them when status changes.


Observer Pattern (simple explanation)
------------------------------------
Observer Pattern is a "one-to-many" design pattern:
- One object = Subject/Publisher (source of updates)
- Many objects = Observers/Subscribers (receivers of updates)

When the Subject state changes, it informs all registered Observers.

Benefits:
- Loose coupling: Subject does not need to know observer internals.
- Easy extension: Add new notification types without changing core flow.
- Better maintainability for event-driven systems.


How this project maps to the pattern
------------------------------------
1) Observer interface
   - File: src/main/java/org/example/Observer.java
   - Method: updateOrderStatus(String newStatus)
   - Purpose: Contract that all observers must implement.

2) Subject / Publisher
   - File: src/main/java/org/example/OrderService.java
   - Key methods:
     - addUser(Observer user)    -> subscribe
     - removeUser(Observer user) -> unsubscribe
     - notifyToUsers(String newStatus) -> publish status to all subscribers
   - `clientList` stores all active observers.

3) Concrete observers
   - EmailNotifier.java
   - SMSNotifier.java
   - AdminUser.java
   - Each class defines what to do when status is updated.

4) Demo flow
   - File: src/main/java/org/example/Main.java
   - Steps in main():
     - Create OrderService
     - Create Email and SMS observers
     - Subscribe both
     - Send "Packed" update
     - Unsubscribe email observer
     - Send "shipped" update


Project structure (important files)
-----------------------------------
- pom.xml
- src/main/java/org/example/Observer.java
- src/main/java/org/example/OrderService.java
- src/main/java/org/example/EmailNotifier.java
- src/main/java/org/example/SMSNotifier.java
- src/main/java/org/example/AdminUser.java
- src/main/java/org/example/Main.java


Prerequisites
-------------
- Java 8+ (project is configured for Java 1.8 in pom.xml)
- Maven 3.6+


How to run
----------
From project root (`OrderStatusSystem`):

1. Build the project
   mvn -DskipTests package

2. Run the demo class
   java -cp target/classes org.example.Main


Sample output
-------------
The current code prints output similar to:

EmailNotification current status : Packed of jj@gmail.com
SMSNotification current status : Packed of 12345678
SMSNotification current status : shipped of 12345678

Why only 3 lines?
- First update (Packed): email + SMS observers receive update (2 lines)
- Email observer is removed
- Second update (shipped): only SMS observer receives update (1 line)


How to extend this project
--------------------------
Example: Add a WhatsApp notifier

1) Create class implementing Observer
   - public class WhatsAppNotifier implements Observer
   - implement updateOrderStatus(String newStatus)

2) Register it in Main
   - serviceOrd.addUser(new WhatsAppNotifier(...));

3) Run again
   - It will automatically receive future status updates.

You do not need to change `OrderService` to support a new notifier.


Code fixes applied (from review)
---------------------------------
The following issues were found and fixed:
- `OrderService` constructor had a self-assignment bug (`this.updatedStatus = updatedStatus`)
  that always set the field to null. Removed the dead line.
- `OrderService.addUser()` now rejects null observers to prevent NullPointerException.
- `OrderService.notifyToUsers()` now iterates over a snapshot copy of the observer list
  to prevent ConcurrentModificationException if an observer unsubscribes during notification.
- `OrderService.notifyToUsers()` parameter renamed from `newStauts` to `newStatus`.
- `EmailNotifier` print label corrected from "SMSNotification" to "EmailNotification".
- `EmailNotifier` field renamed from `emilId` to `emailId`.
- `SMSNotifier.mobileNumber` type changed from `int` to `long` to support real phone numbers
  (int overflows above 2,147,483,647).


Quick recap
-----------
- `OrderService` is the publisher.
- Notifier classes are subscribers.
- Subscribe/unsubscribe controls who gets updates.
- Observer Pattern makes notification systems clean and extensible.

