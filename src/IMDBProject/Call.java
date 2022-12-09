package IMDBProject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Class for calling IMDB's API for the information on movies.
 *
 * @author Erin Crowley
 * @author Chase Coleman
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class Call {

  public static void main(String[] args) throws IOException {
    ArrayList<Movie> movies = (ArrayList<Movie>) Call.makeAPICall("project x");
    System.out.println(movies.get(0).getImage());
    System.out.println(Call.getDescription(movies.get(0).getId()));
  }


  /**
   * Calls the API to get results and info.
   *
   * @param submission the users string input
   * @return a List of the search results as movie objects
   * @throws IOException if input is unreadable
   */
  public static List<Movie> makeAPICall(String submission) throws IOException {
    String link = "https://imdb-api.com/en/API/SearchMovie/k_mcx0w8kk/" + submission;
    return readMovieList(api(link));
  }

  /**
   * Creates a JTree movie list from the API JSON file of the users search input.
   *
   * @param input the user's string input
   * @return a List of the search results as movie objects
   * @throws IOException if input is unreadable
   */
  public static List<Movie> readMovieList(JsonNode input) throws IOException {
    // Set-up:
    Movie temp;
    List<Movie> searchResults = new ArrayList<>();


    // Create JTree
    JsonNode tree = input;
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
    return searchResults;
  }

  /**
   * Gets all the actors from the movie.
   *
   * @param id of the movie for api call
   * @return Arraylist of actor names
   * @throws IOException if the url messes up
   */
  public static ArrayList<String> getActors(String id) throws IOException{
    String link = "https://imdb-api.com/en/API/FullCast/k_mcx0w8kk/" + id;
    JsonNode tree = api(link);
    JsonNode results = tree.get("actors");
    ArrayList<String> actors = new ArrayList<>();
    for (JsonNode result : results) {
      String all = result.toString();
      String name = all.substring(all.indexOf("name") + 7, all.indexOf("asCharacter") - 3);
      actors.add(name);
    }
    return actors;
  }

  /**
   * Gets short description of movie from wiki api call.
   *
   * @param id of the movie
   * @return the description as a html tag so it wraps
   * @throws IOException if the url messes up
   */
  public static String getDescription(String id) throws IOException {
    String link = "https://imdb-api.com/en/API/Wikipedia/k_mcx0w8kk/" + id;
    JsonNode tree = api(link);
    String rough = tree.get("plotShort").toString();
    System.out.println(rough);
    int rightBound = -1;
    if (rough.contains("\",\"")) {
        rightBound = rough.indexOf("\",\"");
    }
    if (rough.contains("\\r\\n")) {
      rightBound = rough.indexOf("\\r\\n");
    }
    if (rightBound < 14) {
      rightBound = rough.length();
    }
    String polished = rough.substring(14, rightBound);
    return "<HTML>" + polished + "</HTML>";
  }

  /**
   * Gets a cute pic of the actor/actress
   * @param name of the star
   * @return a string link to the image
   * @throws IOException if something goes wrong for the api
   */
  public static String getActorImage(String name) throws IOException {
    String link = "https://imdb-api.com/en/API/SearchName/k_mcx0w8kk/" + name;
    JsonNode tree = api(link);
    String answer = tree.get("results").get(0).get("image").toString();
    answer = answer.substring(1, answer.length() - 1);
    System.out.println(answer);
    return answer;
  }

  /**
   * Gets movies that actor is in
   * @param name of actor
   * @return movies the actor is in
   * @throws IOException if there is a problem searching using the api
   */
  public static ArrayList<String> getMovies(String name) throws IOException {
    String link = "https://imdb-api.com/en/API/SearchName/k_mcx0w8kk/" + name;
    JsonNode tree = api(link);
    JsonNode nodes = tree.get("results");
    ArrayList<String> result = new ArrayList<>();
    for (JsonNode node : nodes) {
      String actorName = node.get("title").toString();
      actorName = actorName.substring(1, actorName.length() - 1);
      if (name.equals(actorName)) {
        String desc = node.get("description").toString();
        result.add(desc.substring(desc.indexOf(", ") + 1, desc.indexOf("(") - 1));
      }
    }
    return result;
  }

  public static JsonNode api(String link) throws IOException {
    URL url = new URL(link);
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

    ArrayList<String> result = new ArrayList<>();
    // Create JTree
    ObjectMapper map = new ObjectMapper();
    JsonNode tree = map.readTree(input);
    return tree;
  }

  public static List<Movie> generateRandom() throws IOException {

    URL url = new URL("https://imdb-api.com/en/API/Top250Movies/k_mcx0w8kk");

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
    return readRandomList(input);
  }

  public static List<Movie> readRandomList(InputStream input) throws IOException {

    // Set-up:
    Movie temp;
    List<Movie> searchResults = new ArrayList<>();
    ObjectMapper map = new ObjectMapper();

    // Create JTree
    JsonNode tree = map.readTree(input);
    JsonNode results = tree.get("items");

    // Iterate through result array and make movie objects
    for (int i = 0; i < results.size(); i++) {

      String id = results.get(i).get("id").asText();
      String image = results.get(i).get("image").asText();
      String title = results.get(i).get("title").asText();
      String rank = results.get(i).get("rank").asText();

      // Add to movie object array
      temp = new Movie(id, "Movie", image, title, "Rank " + rank + " out of top 250 movies");
      searchResults.add(temp);

    }

    input.close();
    return searchResults;
  }

}
