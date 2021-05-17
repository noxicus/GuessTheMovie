import java.util.ArrayList;

public class Game {

    public int nubMovies = 0;      // movies counter
    boolean win = false;

    /**
     * Method that returns random movie from ArrayList
     *
     * @param arrayOfMovies ArrayList of movies
     * @return random movie from list
     */

    public String getRandomMovie(ArrayList<String> arrayOfMovies) {

        // generate random number for picking random movie
        nubMovies = arrayOfMovies.size();
        double randomNum = Math.random() * nubMovies;
        int randomInt = (int) randomNum;

        //random movie from array list
        String movieToBeGuessed = arrayOfMovies.get(randomInt);

        //name of movie replaced with '_'
        //movieTBGUnderline = movieToBeGuessed.replaceAll("\\w", "_");
        return movieToBeGuessed;
    }

    /**
     * Compares if user input is contained in movieToBeGuessed string
     * If does, letter is reviled in movieUnderline
     *
     * @param movieToBeGuessed
     * @param movieUnderLine
     * @param ch               user input character
     * @return String that contains reviled letters
     */
    public String checkCorrect(String movieToBeGuessed, String movieUnderLine, char ch) {
        char[] chars1 = movieToBeGuessed.toCharArray();
        char[] chars2 = movieUnderLine.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == ch) {
                chars2[i] = ch;
            }
        }
        return String.valueOf(chars2);
    }

    /**
     * Method to check if string contains underscore therefore has the player win the game
     */
    public boolean checkForFinish(String movie) {
        return !movie.contains("_");
    }
}
