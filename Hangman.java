import java.util.Scanner;

// it is data class
/*
 *  class UserMoveIndex {
 *   private int userGuessIndex;
 *   private boolean isPresent;
 * 
 *   public int userGuessIndex() {
 *    returnn userGuessIndex
 *   }
 * 
 *   public boolean isPresent() {
 *    returnnn isPresent
 *   }
 * 
 * }
 * 
 */
record UserMoveIndex(int userGuessIndex, boolean isPresent) {
};

public class Hangman {
    // magic number
    private final static int MAX_GUESS_COUNT = 7;

    private final static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public void play(Scanner sc) {
        greet(sc);
        HangmanStatus status = new HangmanStatus(randomWord());

        // MAGIC NNUMBER
        while (status.getNumOfWrongGuess() != MAX_GUESS_COUNT) {
            status.printGameCurrentStatus();
            char guessChar = readNextGuessChar(sc);
            // Feature envy code smell
            UserMoveIndex guessCharIndexInWord = status.getUserMoveIndexFor(guessChar);
            if (guessCharIndexInWord.isPresent()) {
               status.updateGuessWordStatus(guessChar, guessCharIndexInWord);
                if(status.isWin()){
                    printGameWinStatus(status);
                    break;
                }
            } else {
                status.updateWrongMoveFlag(guessChar);
            }

        }

        if (!status.isWin()) {
            System.out.println("\nRip!");
            System.out.println("The Word was: " + status.getRandWord());
        } 

    }

    private void printGameWinStatus(HangmanStatus status) {
        System.out.println("\n");
        System.out.println(status.getCurrentGallow());
        System.out.print("Word: ");
        printString(status.getArrayOfCorrectLetter());
        System.out.println("\n\nGood Work!");
        System.out.println("\n");
        
    }

    private char readNextGuessChar(Scanner scanner) {
        System.out.print("\nGuess: ");
        char letter = scanner.next().charAt(0);
        return letter;
    }

    // function for returning random word.
    public String randomWord() {
        double index = Math.random() * 64; // we have 64 total no words;
        int ind = (int) index;
        return words[ind];
    }

    private void greet(Scanner scanner) {
        System.out.println("\nWelcome! ");
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println(
                "Hi " + name + " \nwe have a game for you. Hangman.\n type anything on keyword for playing game.");
        scanner.nextLine();
        System.out.println("Hangman is guessing game.");
        System.out.println("\nRules:");
        System.out.println(
                "\tcomputer well select any word from database. you will Guesses that word by letter by letter .\n\t if you Guessed wrong letter  more than 6 times game will be over.you will be lost");

    }

    private void printString(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
