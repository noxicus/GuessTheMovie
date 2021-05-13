import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessTheMovie {
    public static void main(String[] args) throws FileNotFoundException {

        int indexOfLetter = 99;
        int wrongLettCount = 0;
        String wrongCharacter = "";
        String wrongCharacters = "";

        Game game1 = new Game();
        String movieToBeGuessed = game1.getRandomMovie();
        System.out.println(movieToBeGuessed);
        String movieToBeGuessedUnderline = movieToBeGuessed.replaceAll("\\w", "_");

        for (int i = 10; i > 0; i--) {
            System.out.println("You are guessing " + movieToBeGuessedUnderline);
            System.out.println(i + " more trys.");
            System.out.println("You have guessed (" + wrongLettCount + ") wrong letters: " + wrongCharacters);
            System.out.println("Guess the letter: ");
            Scanner scanner = new Scanner(System.in);
            char input = scanner.next().charAt(0);

            indexOfLetter = movieToBeGuessed.indexOf(input);

            if (indexOfLetter >= 0 && indexOfLetter <= movieToBeGuessed.length()) {
                movieToBeGuessedUnderline = game1.checkCorrect(movieToBeGuessed, movieToBeGuessedUnderline, input);

            } else {
                wrongLettCount++;
                wrongCharacter = Character.toString(input);
                wrongCharacters = wrongCharacters + wrongCharacter + ", ";

            }
            if (game1.checkForFinish(movieToBeGuessedUnderline)) {
                System.out.println("Congratulations.\nYou won.");
                break;
            } else if (i == 1) {
                System.out.println("No more trays, you lost a game !");
            }
        }
    }
}
