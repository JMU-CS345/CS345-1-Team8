package IMDBProject;

import java.util.List;

/**
 * Class for easily printing movie information.
 *
 * @author Erin Crowley
 * @author Chase Coleman
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class MovieListPrinter {

  public static String print(List<Movie> movies) {

  // String format for the whole movie object:
  // String.format("id: " + movies.get(i).getId() + "\nresultType: " + movies.get(i).getResultType() +
  //    "\nimage: " + movies.get(i).getImage() + "\ntitle: " + movies.get(i).getTitle() + "\ndescription: " +
  //    movies.get(i).getDescription() + "\n");

    String answer = "";
    for (Movie movie : movies) {
      answer += movie.getTitle();
      answer += "\n";
    }
    return answer;
  }
}
