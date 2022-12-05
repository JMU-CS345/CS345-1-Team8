package IMDBProject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class iMDBTest {

  @Test
  void testMovieInfoJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/SearchMovie the batman.json");
    ArrayList<Movie> movieList = MovieListReader.readMovieList(in);
    Movie testMovie = movieList.get(0);

    assertEquals("tt1877830", testMovie.getId());
    assertEquals("2022 Robert Pattinson, Zoë Kravitz", testMovie.getDescription());
    assertEquals("https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_Ratio0.6757_AL_.jpg", testMovie.getImage());
    assertEquals("Movie", testMovie.getResultType());
    assertEquals("The Batman", testMovie.getTitle());
  }

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
  void testMovieTitleCorrectJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/SearchMovie the batman.json");
    ArrayList<Movie> movieList = MovieListReader.readMovieList(in);
    Movie testMovie = movieList.get(0);
    assertEquals("The Batman", testMovie.getTitle());
  }

  @Test
  void testActorNamesCorrectJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/FullCast the batman.json");
    ObjectMapper map = new ObjectMapper();
    JsonNode tree = map.readTree(in);

    String actor = tree.get("actors").get(0).get("name").asText();
    assertEquals("Robert Pattinson", actor);
  }

  @Test
  void testGuiIsVisible() {
    iMDBGui testGui = new iMDBGui();
    testGui.setVisible(true);
    assertTrue(testGui.isVisible());
  }

  @Test
  void testSearchResultsGuiJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/SearchMovie the batman.json");
    ArrayList<Movie> movieList = MovieListReader.readMovieList(in);

    SearchResultsGui testSearchResultsGui = new SearchResultsGui(movieList);

    testSearchResultsGui.setVisible(true);
    assertTrue(testSearchResultsGui.isVisible());
  }
  @Test
  void testMovieGuiVisibleJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/SearchMovie the batman.json");
    ArrayList<Movie> movieList = MovieListReader.readMovieList(in);
    Movie testMovie = movieList.get(0);

    MovieGui testMovieGui = new MovieGui(testMovie);
    testMovieGui.setVisible(true);
    assertTrue(testMovieGui.isVisible());
  }

  @Test
  void testMovieListPrinterJSON() throws IOException {
    InputStream in = iMDBTest.class.getResourceAsStream("/JSON/SearchMovie the batman.json");
    ArrayList<Movie> movieList = MovieListReader.readMovieList(in);
    String list = MovieListPrinter.print(movieList);
    assertEquals(list, MovieListPrinter.print(movieList));
  }

  @Test
  void testActorGuiVisible() throws IOException {
    ActorGui testGui = new ActorGui("Robert Pattinson");
    testGui.setVisible(true);
    assertTrue(testGui.isVisible());
  }
}
