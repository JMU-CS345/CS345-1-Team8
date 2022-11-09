package IMDBProject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * So you don't have to search movie from beginning with api.
 * Now you can just skip to moviegui from the searchresults selection.
 */
public class ShortcutMovieGuiDriver {
  public static void main(String[] args) throws IOException {
    // making a movie from real iMDB api call results
    Movie movie = new Movie("tt1636826", "resultType",
        "https://m.media-amazon.com/images/M/MV5BMTc1MTk0Njg4OF5BMl5BanBnXkFtZTcwODc0ODkyNw@@._V1_Ratio0.6757_AL_.jpg",
        "Project X", Call.getDescription("tt1636826"));
    // shortcutting the gui after you click on the movie from selection tree
    new MovieGui(movie);
  }
}
