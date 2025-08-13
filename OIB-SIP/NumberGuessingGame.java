import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;
        final int MAX_ROUNDS = 3;

        System.out.println("=== Welcome to the Number Guessing Game ===");

        while (round <= MAX_ROUNDS) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int attempts = 0;
            final int MAX_ATTEMPTS = 7;
            boolean hasGuessedCorrectly = false;

            System.out.println("\n--- Round " + round + " ---");
            System.out.println("Guess the number between 1 and 100. You have " + MAX_ATTEMPTS + " attempts.");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess;

                // Validate integer input
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                } else {
                    System.out.println("Please enter a valid number!");
                    scanner.next(); // Clear invalid input
                    continue;
                }

                attempts++;

                if (guess == numberToGuess) {
                    System.out.println("🎉 Congratulations! You guessed the number in " + attempts + " attempt(s).");
                    int score = (MAX_ATTEMPTS - attempts + 1) * 10;
                    System.out.println("You earned " + score + " points.");
                    totalScore += score;
                    hasGuessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("❌ Sorry! You've used all attempts. The correct number was: " + numberToGuess);
            }

            round++;
        }

        System.out.println("\n=== Game Over ===");
        System.out.println("Your total score: " + totalScore + " points.");
        System.out.println("Thanks for playing!");
    }
}
