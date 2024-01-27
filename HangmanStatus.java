public class HangmanStatus {
    private final static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
            "|   |\n" +
            "O   |\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|   |\n" +
            "     |\n" +
            "     |\n" +
            " =========\n",

            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                            // character, which also happens to be '\'
            "     |\n" +
            "     |\n" +
            " =========\n",

            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "/    |\n" +
            "     |\n" +
            " =========\n",

            " +---+\n" +
            " |   |\n" +
            " O   |\n" +
            "/|\\  |\n" +
            "/ \\  |\n" +
            "     |\n" +
            " =========\n" };
            
    private final String randWord;
    private char[] arrayOfCorrectLetter;
    private String userWrongInputWord;
    private int noOfCorrectLetter = 0;
    private int numOfWrongGuess = 0;
    private int indexOfGallows = 0;

    public HangmanStatus(String randWord) {
        this.randWord = randWord;
        this.arrayOfCorrectLetter = prepareBlankGuessWord(randWord);
        this.userWrongInputWord = "";
    }

    private char[] prepareBlankGuessWord(String randWord) {
        char[] arrayOfCorrectLetter = new char[randWord.length()];

        for (int i = 0; i < arrayOfCorrectLetter.length; i++) {
            arrayOfCorrectLetter[i] = '_';
        }

        return arrayOfCorrectLetter;
    }

    public void updateUserLetter(char letter, int index) {
        arrayOfCorrectLetter[index] = letter;
    }

    public String getRandWord() {
        return this.randWord;
    }

    public void printGameCurrentStatus() {
        System.out.println("\n");
        System.out.println(gallows[indexOfGallows]);

        System.out.print("Word: ");
        printString(arrayOfCorrectLetter);

        System.out.println("\n\nMisses: " + userWrongInputWord);
    }

    private void printString(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public int getNumOfWrongGuess() {
        return this.numOfWrongGuess;
    }

    public char[] getArrayOfCorrectLetter() {
        return this.arrayOfCorrectLetter;
    }

    public UserMoveIndex getUserMoveIndexFor(char guessChar) {
        for (int i = 0; i < this.randWord.length(); i++) {
            if (guessChar == this.randWord.charAt(i)) {
                if (this.arrayOfCorrectLetter[i] != guessChar) {
                    return new UserMoveIndex(i, true);
                }
            }
        }
        return new UserMoveIndex(-1, false);
    }

    public void updateGuessWordStatus(char guessChar, UserMoveIndex guessCharIndexInWord) {
        arrayOfCorrectLetter[guessCharIndexInWord.userGuessIndex()] = guessChar;
        noOfCorrectLetter++;
    }

    public void updateWrongMoveFlag(char guessChar) {
                numOfWrongGuess++;
                indexOfGallows++;
                userWrongInputWord += guessChar;
    }

    public int getIndexOfGallows() {
        return this.indexOfGallows;
    }

    public boolean isWin() {
        return noOfCorrectLetter == randWord.length();
    }

    public String getCurrentGallow() {
        return gallows[indexOfGallows];
    }

}

