package IMDBProject;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class MovieGui extends JFrame {

  private Movie movie;
  private ArrayList<String> actors;
  private JPanel displayPanel;


  public MovieGui(Movie movie) throws IOException {
    this.movie = movie;
    this.actors = Call.getActors(movie.getId());
    buildGui();
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Helper method for building the GUI.
   */
  private void buildGui() throws IOException {
    getContentPane().add(getDisplayPanel());
  }

  private Component getDisplayPanel() throws IOException {
    displayPanel = new JPanel(new BorderLayout());
    displayPanel.setPreferredSize(new Dimension(800, 800));
    buildContents();
    return displayPanel;
  }

  /**
   * Builds our contents within the display panel.
   *
   * @throws IOException if the url messes up from nested methods
   */
  private void buildContents() throws IOException {
    // image added to display panel by itself
    try {
      URL icon = new URL(movie.getImage());
      ImageIcon image = new ImageIcon(icon);
      while (image.getIconHeight() > 800 || image.getIconWidth() > 800) {
        image = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth() / 2,
            image.getIconHeight() / 2, Image.SCALE_DEFAULT));
      }
      displayPanel.add(new JLabel(image), BorderLayout.WEST);
    } catch (Exception e) {
      System.out.println("Problem with image of movie");
    }
    // description stuff is in its own panel inside main displayPanel
    JPanel descPanel = new JPanel(new BorderLayout());
    displayPanel.add(descPanel, BorderLayout.CENTER);

    JLabel movieLabel = new JLabel(movie.getTitle());
    movieLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
    movieLabel.setMaximumSize(new Dimension(100, 100));

    JLabel descLabel = new JLabel(movie.getDescription());
    descLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    descLabel.setMaximumSize(new Dimension(100, 100));

    JLabel longDescLabel = new JLabel(Call.getDescription(movie.getId()));
    longDescLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

    descPanel.add(movieLabel, BorderLayout.NORTH);

    // Actor tree
    descPanel.add(new JScrollPane(getTreeView()), BorderLayout.EAST);

    displayPanel.add(longDescLabel, BorderLayout.SOUTH);
  }

  private Component getTreeView() {
    JTree tree = new JTree();
    tree.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
    for (String actor : actors) {
      rootNode.add(new DefaultMutableTreeNode(actor));
    }
    TreeModel model = new DefaultTreeModel(rootNode);
    tree.setModel(model);
    tree.setRootVisible(false);

    // Change icon
    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
    Icon closedIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/actor_icon.png")));
    Icon openIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/actor_icon.png")));
    Icon leafIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/actor_icon.png")));
    renderer.setClosedIcon(closedIcon);
    renderer.setOpenIcon(openIcon);
    renderer.setLeafIcon(leafIcon);

    tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Object selectedNode = e.getPath().getLastPathComponent();
        if (selectedNode instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
          Object selectedObject = node.getUserObject();
          if (selectedObject instanceof String) {
            try {
              new ActorGui((String) selectedObject);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
      }
    });
    return new JScrollPane(tree);
  }


}
