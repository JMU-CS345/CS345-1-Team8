package IMDBProject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.net.URL;

/**
 * GUI specifically for displaying the chosen movie information like pictures, actors, ect.
 */
public class MovieGui extends JFrame {

  private Movie movie;
  private JPanel displayPanel;


  public MovieGui(Movie movie) {
    this.movie = movie;
    buildGui();
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Helper method for building the GUI.
   */
  private void buildGui() {
    getContentPane().add(getDisplayPanel());
  }

  private Component getDisplayPanel() {
    this.displayPanel = new JPanel(new BorderLayout());
    displayPanel.setPreferredSize(new Dimension(800, 800));
    buildContents();
    return displayPanel;
  }

  /**
   * Builds contents of the displayPanel and adds them.
   */
  private void buildContents() {
    // image added to display panel by itself
    try {
      URL icon = new URL(movie.getImage());
      ImageIcon image = new ImageIcon(icon);
      System.out.println(image.getIconHeight());
      while (image.getIconHeight() > 1000) {
        image = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth() / 2,
                image.getIconHeight() / 2, Image.SCALE_DEFAULT));
      }
      displayPanel.add(new JLabel(image), BorderLayout.WEST);
    } catch (Exception e) {
      System.out.println("Problem with image of movie");
    }

    // description is in its own panel inside main displayPanel
    JPanel descPanel = new JPanel(new BorderLayout());

    JLabel movieLabel = new JLabel(movie.getTitle());
    movieLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    JLabel descLabel = new JLabel(movie.getDescription());
    descLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

    descPanel.add(movieLabel, BorderLayout.CENTER);
    descPanel.add(descLabel, BorderLayout.SOUTH);
    displayPanel.add(descPanel, BorderLayout.EAST);
  }

}
