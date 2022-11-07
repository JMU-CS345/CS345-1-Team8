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

    String answer = "";
    for (Movie movie : movies) {
      answer += movie.getTitle();
      answer += "\n";
    }
    return answer;
  }
}
