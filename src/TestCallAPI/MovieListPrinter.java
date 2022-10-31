package TestCallAPI;

import java.util.List;

public class MovieListPrinter {

  public void print(List<Movie> movies) {
    for (int i = 0; i < movies.size(); i++) {
      System.out.println("id: " + movies.get(i).getId() + "\nresultType: " + movies.get(i).getResultType() +
              "\nimage: " + movies.get(i).getImage() + "\ntitle: " + movies.get(i).getTitle() +
              "\ndescription: " + movies.get(i).getDescription() + "\n");
    }
  }
}
