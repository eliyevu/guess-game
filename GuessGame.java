import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GuessGame {
    public static void main(String[] args) {
        //Fields
        int guessNumber;
        String gameMode;
        String playAgain = "Yes";
        boolean hasWon = false;
        //Create scanner and random objects
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        while (playAgain.equalsIgnoreCase("Yes")) {
            //Generate a random number from 1 to 100 to be guessed
            guessNumber = random.nextInt(100) + 1;
            //Time limits in seconds
            int timeLimit;
            //Prompt user to select game mode
            while (true) {
                //Game rules
                System.out.println("Welcome to the Guessing Game!");
                System.out.println("You have the chance to guess a number between 1 and 100");
                System.out.println("""
                        Game Mode
                        1 > Easy(60 seconds)
                        2 > Medium(40 seconds)
                        3 > Hard(20 seconds)""");
                System.out.print("Enter your choice: ");
                gameMode = scanner.nextLine().toLowerCase();

                switch (gameMode) {
                    case "1":
                        timeLimit = 60;
                        break;
                    case "2":
                        timeLimit = 40;
                        break;
                    case "3":
                        timeLimit = 20;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid choice");
                        continue;
                }
                break;
            }
            //Timer
            boolean[] timeUp = {false};
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                public void run(){
                    timeUp[0] = true;
                    System.out.println("\nTime's up!");
                }
            }, timeLimit * 1000);

            System.out.println("You have " + timeLimit + " seconds to guess the number");

            //Game loop
            while (!timeUp[0]) {
                //Prompt the user to make a guess
                System.out.print("Enter your guess: ");

                int userGuess;
                if (scanner.hasNextInt()) {
                    userGuess = scanner.nextInt();
                    scanner.nextLine();
                    //Check if the timer has expired
                    if (timeUp[0]) {
                        break;
                    }

                    if (userGuess == guessNumber) {
                        System.out.println("Congratulation! You have guessed the correct number!");
                        hasWon = true;
                        break;
                    } else if (userGuess < guessNumber) {
                        System.out.println("Your guess is too low! Try again.");
                    } else {
                        System.out.println("Your guess is too high! Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a number between 1 and 100.");
                    scanner.nextLine();
                }
            }
            timer.cancel(); // Stop timer
            if (!hasWon && timeUp[0]) {
                System.out.println("The correct guess number was " + guessNumber);
            }

            //Ask user to continue playing the game
            System.out.print("Would you like to play again? Yes or No: ");
            playAgain = scanner.nextLine().toLowerCase();
        }
        System.out.println("Thank you for playing!.");
        scanner.close();
    }
}
