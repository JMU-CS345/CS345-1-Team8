package IMDBProject;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GUI for user input and navigation.
 *
 * @author Erin Crowley
 * @author Chase Coleman
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class iMDBGui extends JFrame {

  private JFrame main;
  private JPanel displayPanel;
  private JTextField entry;
  private JButton search;

  private JButton random;
  private JButton leo;

  /**
   * Constructor for main.
   */
  public iMDBGui() {
    this.main = this;
    main.setSize(new Dimension(450, 100));
    main.setLocationRelativeTo(null);

    buildGui();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
    main.getRootPane().setDefaultButton(search);
  }

  /**
   * Helper method for building GUI.
   */
  private void buildGui() {
    getContentPane().add(getDisplayPanel());
  }

  /**
   * Helper method for creating display panel.
   *
   * @return this GUI's display panel as a Jpanel
   */
  private Component getDisplayPanel() {
    this.displayPanel = new JPanel();
    displayPanel.setPreferredSize(new Dimension(450, 100));
    buildContents();
    return displayPanel;
  }

  /**
   * Helper method for building the contents of the display panel.
   */
  private void buildContents() {

    // Title creation
    JPanel titlePanel = new JPanel();
    JLabel title = new JLabel("Enter a Movie Title: ");
    title.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    titlePanel.add(title);

    // User input panel creation
    JPanel inputPanel = new JPanel();
    this.entry = new JTextField();
    entry.setPreferredSize(new Dimension(100, 25));
    inputPanel.add(entry);

    // Search button creation
    JPanel searchPanel = new JPanel();
    createSearchButton();
    searchPanel.add(search);

    displayPanel.add(titlePanel);
    displayPanel.add(inputPanel);
    displayPanel.add(searchPanel);

    createRandomButton();
    createLeoButton();
  }

  /**
   * Helper method for creating the search button.
   *
   * <p>Contains a pop-up window with a text box and a search button with and
   * action listener that activates the API call to create the search results.
   * It takes a few seconds as it secures the URL connection and changes the GUI.</p>
   */
  private void createSearchButton() {
    this.search = new JButton("Search");
    search.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));

    // Action listener for when the button is clicked
    search.addActionListener(e -> {

      // User input as a search request
      String submission = entry.getText();

      // Case for no input
      if (!submission.equals("")) {
        System.out.println(submission);

        try {
          ArrayList<Movie> movies = (ArrayList<Movie>) Call.makeAPICall(submission);
          SearchResultsGui SRGui = new SearchResultsGui(movies);
          SRGui.setVisible(true);

        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        // get array of results back from Erin's code
        // display array of stuff to new window
      }
    });
  }
  private void createRandomButton() {
    this.random = new JButton("I'm feeling lucky!");
    random.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));

    random.addActionListener(e -> {

      try {
        ArrayList<Movie> top250 = (ArrayList<Movie>) Call.generateRandom();

        Random rand = new Random();
        int randIndex = rand.nextInt(250);

        new MovieGui(top250.get(randIndex));

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    displayPanel.add(random);
  }

  private void createLeoButton() {
    this.leo = new JButton("Leo Dating Game");
    leo.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
    leo.addActionListener(e -> {
      try {
        new Leo();
      } catch (MalformedURLException ex) {
        throw new RuntimeException(ex);
      }
    });
    displayPanel.add(leo);
  }
}
