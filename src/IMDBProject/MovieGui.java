package IMDBProject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;

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
      displayPanel.add(new JLabel(new ImageIcon(icon)), BorderLayout.WEST);
    } catch (Exception e) {
      System.out.println("Problem with image of movie");
    }

    // description stuff is in its own panel inside main displayPanel
    JPanel descPanel = new JPanel(new BorderLayout());
    descPanel.add(new JLabel(movie.getTitle()), BorderLayout.CENTER);
    descPanel.add(new JLabel(movie.getDescription()), BorderLayout.SOUTH);
    displayPanel.add(descPanel, BorderLayout.EAST);
  }

}
