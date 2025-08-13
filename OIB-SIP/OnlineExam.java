import java.util.*;

public class OnlineExam {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> userDatabase = new HashMap<>();
    static String loggedInUser = null;

    static {
        // Sample user
        userDatabase.put("student", "password123");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Online Exam System ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                login();
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            loggedInUser = username;
            System.out.println("Login successful!");

            userMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("\nWelcome, " + loggedInUser);
            System.out.println("1. Update Profile");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    startExam();
                    break;
                case 3:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    static void updateProfile() {
        System.out.print("Enter new password: ");
        String newPassword = sc.nextLine();
        userDatabase.put(loggedInUser, newPassword);
        System.out.println("Password updated successfully.");
    }

    static void startExam() {
        String[] questions = {
            "1. What is the capital of India?\n a) Mumbai\n b) Delhi\n c) Kolkata\n d) Chennai",
            "2. Who is the founder of Java?\n a) James Gosling\n b) Dennis Ritchie\n c) Guido van Rossum\n d) Bjarne Stroustrup",
            "3. What does JVM stand for?\n a) Java Visual Machine\n b) Java Virtual Machine\n c) Just Virtual Machine\n d) None"
        };

        char[] answers = {'b', 'a', 'b'};
        char[] userAnswers = new char[questions.length];

        int totalTime = 30; // Total exam time in seconds
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < questions.length; i++) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - startTime) / 1000 >= totalTime) {
                System.out.println("\nTime's up! Auto-submitting...");
                break;
            }

            System.out.println("\n" + questions[i]);
            System.out.print("Your answer (a/b/c/d): ");
            userAnswers[i] = sc.nextLine().toLowerCase().charAt(0);
        }

        int score = 0;
        for (int i = 0; i < answers.length; i++) {
            if (i < userAnswers.length && userAnswers[i] == answers[i]) {
                score++;
            }
        }

        System.out.println("\nExam submitted.");
        System.out.println("Your score: " + score + "/" + answers.length);
    }

    static void logout() {
        System.out.println("Logging out...");
        loggedInUser = null;
    }
}
