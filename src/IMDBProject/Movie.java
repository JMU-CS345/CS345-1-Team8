package IMDBProject;

/**
 * Class for easily manipulating movie results.
 *
 * @author Erin Crowley
 * @author Chase
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class Movie {

  private String id;
  private String resultType;
  private String image;
  private String title;
  private String description;

  /**
   * Movie object constructor.
   *
   * @param id          the id used for accessing other information
   * @param resultType  the type of result
   * @param image       the image URL
   * @param title       movie title
   * @param description brief overview of the movie
   */
  public Movie(String id, String resultType,
               String image, String title, String description) {
    this.id = id;
    this.resultType = resultType;
    this.image = image;
    this.title = title;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getResultType() {
    return resultType;
  }

  public void setResultType(String resultType) {
    this.resultType = resultType;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
