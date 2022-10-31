package TestCallAPI;

import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    List<Movie> movies = Call.makeAPICall();
    new MovieListPrinter().print(movies);
  }
}
