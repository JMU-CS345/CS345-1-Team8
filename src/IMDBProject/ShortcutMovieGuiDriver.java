package IMDBProject;

import java.net.MalformedURLException;

/**
 * So you don't have to search movie from beginning with api.
 * Now you can just skip to moviegui from the searchresults selection.
 */
public class ShortcutMovieGuiDriver {
  public static void main(String[] args) throws MalformedURLException {
    // making a movie from real iMDB api call results
    Movie movie = new Movie("id", "resultType",
        "https://m.media-amazon.com/images/M/MV5BMTM0MDgwNjMyMl5BMl5BanBnXkFtZTcwNTg3NzAzMw@@._V1_Ratio0.6757_AL_.jpg",
        "Iron Man 2", "2010 Robert Downey Jr., Mickey Rourke");
    // shortcutting the gui after you click on the movie from selection tree
    new MovieGui(movie);
  }
}
