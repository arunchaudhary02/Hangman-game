import java.util.*;

public class HangmanPlayGround {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.play(sc);
    }

}
