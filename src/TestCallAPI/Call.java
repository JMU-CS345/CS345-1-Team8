package TestCallAPI;

import java.net.URL;
import java.net.HttpURLConnection;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Call {

  private static List<Movie> movieList;

  public static List<Movie> readMovieList(InputStream input) throws IOException {
    Movie temp;
    List<Movie> searchResults = new ArrayList<>();
    ObjectMapper map = new ObjectMapper();
    JsonNode tree = map.readTree(input);

    JsonNode results = tree.get("results");

    for (int i = 0; i < results.size(); i++) {
      String id = results.get(i).get("id").asText();
      String resultType = results.get(i).get("resultType").asText();
      String image = results.get(i).get("image").asText();
      String title = results.get(i).get("title").asText();
      String description = results.get(i).get("description").asText();

      temp = new Movie(id, resultType, image, title, description);
      searchResults.add(temp);
    }
    input.close();
    return searchResults;
  }

  public static List<Movie> makeAPICall() throws IOException {

      //URL https://imdb-api.com/en/API/SearchMovie/<key>/<movie title>
      //Key k_mcx0w8kk

      URL url = new URL("https://imdb-api.com/en/API/SearchMovie/k_mcx0w8kk/spider man");

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();

      //Check if connected successfully
      int responseCode = conn.getResponseCode();

      //Code 200 OK
      if (responseCode != 200) {
        throw new RuntimeException("HttpResponseCode: " + responseCode);
      }

      InputStream input = url.openStream();
      return readMovieList(input);
  }

}
