package TestCallAPI;

import java.util.List;

public class MovieListPrinter {

  public static void print(List<Movie> movies) {
    for (Movie movie : movies) {
      System.out.println("id: " + movie.getId() + "\nresultType: " + movie.getResultType() +
              "\nimage: " + movie.getImage() + "\ntitle: " + movie.getTitle() +
              "\ndescription: " + movie.getDescription() + "\n");
    }
  }
}
