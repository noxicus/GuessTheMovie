import java.util.ArrayList;
import java.util.Scanner;

public class GuessTheMovie {
    private static int indexOfLetter = 99;
    private static int wrongLettCount = 0;
    private static String wrongCharacter = "";
    private static String wrongCharacters = "";
    private static ArrayList <String> arrayListMovies = null;
        public static void main(String[] args) {


        Scanner homeScanner = new Scanner(System.in);
        // Message that it's shown on program start
        System.out.println("Please select one of options: \n 1) New Game\n 2) Add movie\n 3) Delete movie");
        // getting input from user
        int number = homeScanner.nextInt();

            switch (number) {
                case 1 -> {
                    // performs db connect and retrieves ArrayList of movies from MySQL db
                    arrayListMovies = ConnectionDB.list();
                    Game game1 = new Game();
                    // Gets random movie from movie ArrayList
                    String movieToBeGuessed = game1.getRandomMovie(arrayListMovies);
                    System.out.println(movieToBeGuessed);
                    // Replace all letters with underscore
                    String movieToBeGuessedUnderline = movieToBeGuessed.replaceAll("\\w", "_");
                    for (int i = 10; i > 0; i--) {
                        System.out.println("You are guessing " + movieToBeGuessedUnderline);
                        System.out.println(i + " more trays.");
                        System.out.println("You have guessed (" + wrongLettCount + ") wrong letters: " + wrongCharacters);
                        System.out.println("Guess the letter: ");
                        Scanner scanner = new Scanner(System.in);
                        char input = scanner.next().charAt(0);
                        // get index of input letter
                        indexOfLetter = movieToBeGuessed.indexOf(input);
                        // Comparing input letter with letters of movie name
                        // If it's true letter is reviled
                        if (indexOfLetter >= 0 && indexOfLetter <= movieToBeGuessed.length()) {
                            movieToBeGuessedUnderline = game1.checkCorrect(movieToBeGuessed, movieToBeGuessedUnderline, input);

                        }
                        // else count for wrong letters is increased
                        else {
                            wrongLettCount++;
                            wrongCharacter = Character.toString(input);
                            wrongCharacters = wrongCharacters + wrongCharacter + ", ";

                        }
                        // Preforms check if all letters are guessed
                        if (game1.checkForFinish(movieToBeGuessedUnderline)) {
                            System.out.println("Congratulations.\nYou won.");
                            break;
                        }
                        // After 10 trays prompts that game is over
                        else if (i == 1) {
                            System.out.println(" Game is over, no more trays !");
                        }
                    }
                }
                case 2 -> {
                    // Adds movie to database
                    Scanner addScanner = new Scanner(System.in);
                    System.out.println("Write name of the movie you want to add: ");
                    String movieToAdd = addScanner.nextLine();
                    ConnectionDB.add(movieToAdd);
                    addScanner.close();
                }
                case 3 -> {
                    // Delete movie from data base
                    Scanner deleteScanner = new Scanner(System.in);
                    arrayListMovies = ConnectionDB.list();
                    System.out.println(arrayListMovies);
                    System.out.println("Write name of the movie you want to delete: ");
                    String movieToDelete = deleteScanner.nextLine();
                    ConnectionDB.delete(movieToDelete);
                    deleteScanner.close();
                }
                default -> System.out.println("Invalid option. Try again !");
            }
    }
}
