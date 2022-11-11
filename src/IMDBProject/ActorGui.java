package IMDBProject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

public class ActorGui extends JFrame {

  private JFrame main;
  private JPanel displayPanel;
  private String name;

  /**
   * GUI constructor.
   *
   * @param name of the actor to display their image
   */
  public ActorGui(String name) {
    this.main = this;
    this.name = name;
    buildGui();
    this.setVisible(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.pack();
  }

  /**
   * Helper method for building the GUI.
   */
  private void buildGui() {
    getContentPane().add(getDisplayPanel());
  }

  /**
   * Helper method for creating the display panel.
   *
   * @return a Jpanel of the display
   */
  private Component getDisplayPanel() {
    this.displayPanel = new JPanel();
    displayPanel.setPreferredSize(new Dimension(800, 800));

    // image added to display panel by itself
    try {
      URL icon = new URL(Call.getActorImage(name));
      ImageIcon image = new ImageIcon(icon);
      while (image.getIconHeight() > 1000) {
        image = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth() / 2,
            image.getIconHeight() / 2, Image.SCALE_DEFAULT));
      }
      displayPanel.add(new JLabel(image));
    } catch (Exception e) {
      System.out.println("Problem with image of movie");
    }

    return displayPanel;
  }

}
