import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public File movieList;
    public Scanner movieListScanner;
    public String line;
    public String movieToBeGuessed;
    public int nubMovies = 0;      // movies counter
    public ArrayList<String> arrayOfMovies = new ArrayList<String>();      // String array of movies
    boolean win = false;


    public Game() throws FileNotFoundException {
        this.movieList = new File("movies.txt");
        this.movieListScanner = new Scanner(movieList);
    }

    public String getRandomMovie() {

        while (movieListScanner.hasNextLine()) {
            line = movieListScanner.nextLine();
            arrayOfMovies.add(line);
            nubMovies++;
        }
        // generate random number for picking random movie
        double randomNum = Math.random() * nubMovies;
        int randomInt = (int) randomNum;

        //random movie from array list
        movieToBeGuessed = arrayOfMovies.get(randomInt);

        //name of movie replaced with '_'
        //movieTBGUnderline = movieToBeGuessed.replaceAll("\\w", "_");
        return movieToBeGuessed;
    }

    public String checkCorrect(String movie, String movieFinal, char ch) {
        char[] chars1 = movie.toCharArray();
        char[] chars2 = movieFinal.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == ch) {
                chars2[i] = ch;
            }
        }
        return String.valueOf(chars2);
    }

    /**
     * method to check if string contains underscore therefore has the player win the game
     */
    public boolean checkForFinish(String movie) {
        return !movie.contains("_");
    }
}
