package IMDBProject;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class iMDBTest {

  @Test
  void testMovieInfo() {
    Movie testMovie = new Movie("1", "2", "3", "4", "5");
    testMovie.setDescription("Description");
    testMovie.setId("123");
    testMovie.setImage("Image");
    testMovie.setResultType("Movie");
    testMovie.setTitle("Title");

    assertEquals("123", testMovie.getId());
    assertEquals("Description", testMovie.getDescription());
    assertEquals("Image", testMovie.getImage());
    assertEquals("Movie", testMovie.getResultType());
    assertEquals("Title", testMovie.getTitle());

  }

  @Test
  void testMovieTitleCorrect() throws IOException {

    assertEquals("Batman", Call.makeAPICall("batman").get(1).getTitle());
  }

  @Test
  void testActorNamesCorrect() throws IOException {

    String id = Call.makeAPICall("batman").get(1).getId();
    assertEquals("Michael Keaton", Call.getActors(id).get(0));
  }

  @Test
  void testGuiIsVisible() throws IOException {

    iMDBGui testGui = new iMDBGui();
    testGui.setVisible(true);
    assertTrue(testGui.isVisible());
  }

  @Test
  void testSearchResultsGui() throws IOException {

    ArrayList<Movie> movies = new ArrayList<>(Call.makeAPICall("batman"));
    SearchResultsGui testSearchResultsGui = new SearchResultsGui(movies);

    testSearchResultsGui.setVisible(true);
    assertTrue(testSearchResultsGui.isVisible());
  }

  @Test
  void testMovieGuiVisible() throws IOException {

    ArrayList<Movie> movies = new ArrayList<>(Call.makeAPICall("batman"));
    Movie testMovie = movies.get(0);

    MovieGui testMovieGui = new MovieGui(testMovie);
    testMovieGui.setVisible(true);
    assertTrue(testMovieGui.isVisible());

    ActorGui testActorGui = new ActorGui("Zoë Kravitz");
    assertTrue(testActorGui.isVisible());
  }

  @Test
  void testMovieListPrinter() throws IOException {
    ArrayList<Movie> movies = new ArrayList<>(Call.makeAPICall("batman"));

    String list = MovieListPrinter.print(movies);

    assertEquals(list, MovieListPrinter.print(movies));

  }




}
