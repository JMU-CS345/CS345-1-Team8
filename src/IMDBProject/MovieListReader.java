package IMDBProject;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class MovieListReader {

  public static ArrayList<Movie> readMovieList(InputStream input) throws IOException {
    Movie temp;
    ArrayList<Movie> bookCollection = new ArrayList<Movie>();
    ObjectMapper map = new ObjectMapper();
    JsonNode tree = map.readTree(input);

    for (int i = 0; i < tree.size(); i ++) {

      String id = tree.get("results").get(i).get("id").asText();
      String resultType = tree.get("results").get(i).get("resultType").asText();
      String image = tree.get("results").get(i).get("image").asText();
      String title = tree.get("results").get(i).get("title").asText();
      String description = tree.get("results").get(i).get("description").asText();

      temp = new Movie(id, resultType, image, title, description);
      bookCollection.add(temp);
    }
    input.close();
    return bookCollection;
  }
}
