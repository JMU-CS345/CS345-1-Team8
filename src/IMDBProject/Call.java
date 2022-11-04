package IMDBProject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for calling IMDB's API for the information on movies.
 *
 * @author Erin Crowley
 * @author Chase
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class Call {

  /**
   * Calls the API to get results and info.
   *
   * @param submission the users string input
   * @return a List of the search results as movie objects
   * @throws IOException if input is unreadable
   */
  public static List<Movie> makeAPICall(String submission) throws IOException {

    //URL Format: https://imdb-api.com/en/API/SearchMovie/<key>/<movie title>
    //Key:        k_mcx0w8kk

    URL url = new URL("https://imdb-api.com/en/API/SearchMovie/k_mcx0w8kk/" + submission);

    // Get URL connection
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();

    //Check if connected successfully
    int responseCode = conn.getResponseCode();

    //Code 200 OK, anything else throw exception
    if (responseCode != 200) {
      throw new RuntimeException("HttpResponseCode: " + responseCode);
    }

    // Read the contents of the new URL
    InputStream input = url.openStream();
    return readMovieList(input);
  }

  /**
   * Creates a JTree movie list from the API JSON file of the users search input.
   *
   * @param input the user's string input
   * @return a List of the search results as movie objects
   * @throws IOException if input is unreadable
   */
  public static List<Movie> readMovieList(InputStream input) throws IOException {

    // Set-up:
    Movie temp;
    List<Movie> searchResults = new ArrayList<>();
    ObjectMapper map = new ObjectMapper();

    // Create JTree
    JsonNode tree = map.readTree(input);
    JsonNode results = tree.get("results");

    // Iterate through result array and make movie objects
    for (int i = 0; i < results.size(); i++) {

      String id = results.get(i).get("id").asText();
      String resultType = results.get(i).get("resultType").asText();
      String image = results.get(i).get("image").asText();
      String title = results.get(i).get("title").asText();
      String description = results.get(i).get("description").asText();

      // Add to movie object array
      temp = new Movie(id, resultType, image, title, description);
      searchResults.add(temp);
    }

    input.close();
    return searchResults;
  }

}
