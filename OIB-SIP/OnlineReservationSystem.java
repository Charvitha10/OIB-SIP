import java.util.*;

public class OnlineReservationSystem {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>(); // username -> password
    static List<Ticket> tickets = new ArrayList<>();
    static int pnrCounter = 1000;

    static class Ticket {
        int pnr;
        String username;
        String trainNumber;
        String trainName;
        String classType;
        String dateOfJourney;
        String from;
        String to;

        Ticket(int pnr, String username, String trainNumber, String trainName,
               String classType, String dateOfJourney, String from, String to) {
            this.pnr = pnr;
            this.username = username;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.from = from;
            this.to = to;
        }

        public void display() {
            System.out.println("PNR: " + pnr);
            System.out.println("User: " + username);
            System.out.println("Train No: " + trainNumber);
            System.out.println("Train Name: " + trainName);
            System.out.println("Class: " + classType);
            System.out.println("Date of Journey: " + dateOfJourney);
            System.out.println("From: " + from + " To: " + to);
        }
    }

    public static void main(String[] args) {
        // Pre-defined users
        users.put("user", "1234");
        users.put("admin", "admin");

        System.out.println("=== Online Reservation System ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (login(username, password)) {
            System.out.println("Login successful.\n");
            int choice;
            do {
                System.out.println("\n1. Reserve Ticket");
                System.out.println("2. Cancel Ticket");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        reserveTicket(username);
                        break;
                    case 2:
                        cancelTicket(username);
                        break;
                    case 3:
                        System.out.println("Exiting system...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 3);
        } else {
            System.out.println("Invalid login credentials!");
        }
    }

    static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    static void reserveTicket(String username) {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();

        // Simulate auto-fetched train name from number
        String trainName = getTrainName(trainNumber);

        System.out.print("Enter Class Type (e.g. Sleeper, AC): ");
        String classType = scanner.nextLine();

        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("From: ");
        String from = scanner.nextLine();

        System.out.print("To: ");
        String to = scanner.nextLine();

        Ticket ticket = new Ticket(++pnrCounter, username, trainNumber, trainName, classType, dateOfJourney, from, to);
        tickets.add(ticket);
        System.out.println("Ticket reserved successfully! Your PNR is: " + ticket.pnr);
    }

    static void cancelTicket(String username) {
        System.out.print("Enter your PNR Number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean found = false;
        for (Ticket ticket : tickets) {
            if (ticket.pnr == pnr && ticket.username.equals(username)) {
                System.out.println("Ticket details:");
                ticket.display();
                System.out.print("Do you want to cancel this ticket? (yes/no): ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    tickets.remove(ticket);
                    System.out.println("Ticket cancelled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ticket not found or not authorized.");
        }
    }

    static String getTrainName(String trainNumber) {
        // Simulated auto-fetch train name
        switch (trainNumber) {
            case "12345": return "Rajdhani Express";
            case "67890": return "Shatabdi Express";
            case "11223": return "Duronto Express";
            default: return "Generic Express";
        }
    }
}
