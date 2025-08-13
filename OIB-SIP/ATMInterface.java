import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {

    // Class 1: BankAccount
    static class BankAccount {
        private String userName;
        private String userId;
        private String pin;
        private double balance;
        private List<String> transactionHistory;

        public BankAccount(String userName, String userId, String pin) {
            this.userName = userName;
            this.userId = userId;
            this.pin = pin;
            this.balance = 0.0;
            this.transactionHistory = new ArrayList<>();
        }

        public boolean validateLogin(String enteredId, String enteredPin) {
            return this.userId.equals(enteredId) && this.pin.equals(enteredPin);
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add("Deposited: ₹" + amount);
                System.out.println("₹" + amount + " deposited successfully.");
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrawn: ₹" + amount);
                System.out.println("₹" + amount + " withdrawn successfully.");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }

        public void transfer(double amount, String recipient) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add("Transferred: ₹" + amount + " to " + recipient);
                System.out.println("₹" + amount + " transferred to " + recipient + " successfully.");
            } else {
                System.out.println("Transfer failed: Insufficient balance or invalid amount.");
            }
        }

        public void printTransactionHistory() {
            if (transactionHistory.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                System.out.println("Transaction History:");
                for (String record : transactionHistory) {
                    System.out.println("- " + record);
                }
            }
        }

        public double getBalance() {
            return balance;
        }
    }

    // Class 2: ATM
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount("Arman", "user123", "1234");

        System.out.println("==== Welcome to the ATM Interface ====");
        System.out.print("Enter User ID: ");
        String enteredId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (userAccount.validateLogin(enteredId, enteredPin)) {
            System.out.println("Login successful!\n");

            int choice;
            do {
                System.out.println("\n========= ATM Menu =========");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        userAccount.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ₹");
                        double withdrawAmount = scanner.nextDouble();
                        userAccount.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ₹");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient name: ");
                        String recipient = scanner.nextLine();
                        System.out.print("Enter amount to transfer: ₹");
                        double transferAmount = scanner.nextDouble();
                        userAccount.transfer(transferAmount, recipient);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);

        } else {
            System.out.println("Invalid User ID or PIN. Access Denied.");
        }

        scanner.close();
    }
}
