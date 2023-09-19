import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;
    // You can add more user-related attributes here, such as transaction history, etc.

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class ATMProgram {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        // Sample users (replace with actual user data)
        users.put("user1", new User("user1", "1234", 1000));
        users.put("user2", new User("user2", "5678", 500));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            System.out.println("Welcome, " + userId + "!");
            ATMFunctionality(userId, scanner);
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }

    private static boolean authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(pin);
    }

    private static void ATMFunctionality(String userId, Scanner scanner) {
        while (true) {
            System.out.println("\nATM Functionality:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    viewBalance(userId);
                    break;
                case 2:
                    withdraw(userId, scanner);
                    break;
                case 3:
                    deposit(userId, scanner);
                    break;
                case 4:
                    // Implement transfer functionality
                    System.out.println("Transfer functionality not implemented yet.");
                    break;
                case 5:
                    // Implement transaction history functionality
                    System.out.println("Transaction history functionality not implemented yet.");
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewBalance(String userId) {
        User user = users.get(userId);
        System.out.println("Your balance is: $" + user.getBalance());
    }

    private static void withdraw(String userId, Scanner scanner) {
        User user = users.get(userId);

        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline

        if (amount > 0 && amount <= user.getBalance()) {
            double newBalance = user.getBalance() - amount;
            user.setBalance(newBalance);
            System.out.println("Withdrawal successful. Your new balance is: $" + newBalance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private static void deposit(String userId, Scanner scanner) {
        User user = users.get(userId);

        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline

        if (amount > 0) {
            double newBalance = user.getBalance() + amount;
            user.setBalance(newBalance);
            System.out.println("Deposit successful. Your new balance is: $" + newBalance);
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

