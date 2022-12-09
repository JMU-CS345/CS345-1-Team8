package IMDBProject;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActorGui extends JFrame {

  private JFrame main;
  private JPanel displayPanel;
  private String name;


  /**
   * GUI constructor.
   *
   * @param name of the actor to display their image
   */
  public ActorGui(String name) throws IOException {
    GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = graphics.getDefaultScreenDevice();

    this.main = this;
    this.name = name;
    buildGui();
    this.setVisible(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.pack();

    device.setFullScreenWindow(this);
  }

  /**
   * Helper method for building the GUI.
   */
  private void buildGui() throws IOException {
    getContentPane().add(getDisplayPanel());
  }

  /**
   * Helper method for creating the display panel.
   *
   * @return a Jpanel of the display
   */
  private Component getDisplayPanel() throws IOException {
    this.displayPanel = new JPanel(new BorderLayout());
    displayPanel.setPreferredSize(new Dimension(1200, 800));

    JLabel actorLabel = new JLabel(name);
    actorLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
    actorLabel.setMaximumSize(new Dimension(100, 100));
    displayPanel.add(actorLabel, BorderLayout.NORTH);
    // image added to display panel by itself
    try {
      URL icon = new URL(Call.getActorImage(name));
      ImageIcon image = new ImageIcon(icon);
      while (image.getIconHeight() > 1000) {
        image = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth() / 2,
            image.getIconHeight() / 2, Image.SCALE_DEFAULT));
      }
      displayPanel.add(new JLabel(image), BorderLayout.WEST);
    } catch (Exception e) {
      System.out.println("Problem with image of movie");
    }
    // add info to display panel
    JLabel longDescLabel = new JLabel("Movies that " + name + " stars in:");
    longDescLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    displayPanel.add(longDescLabel, BorderLayout.CENTER);
    displayPanel.add(new JScrollPane(getTreeView()), BorderLayout.EAST);

    return displayPanel;
  }

  private Component getTreeView() throws IOException {
    // make tree of movies that actor is in
    JTree tree = new JTree();
    tree.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
    ArrayList<String> movies = Call.getMovies(name);
    for (String name : movies) {
      rootNode.add(new DefaultMutableTreeNode(name));
    }
    TreeModel model = new DefaultTreeModel(rootNode);
    tree.setModel(model);
    tree.setRootVisible(false);

    // Change icon
    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
    Icon closedIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/movie_icon.jpg")));
    Icon openIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/movie_icon.jpg")));
    Icon leafIcon = new ImageIcon(Objects.requireNonNull(
        this.getClass().getResource("/Images/movie_icon.jpg")));
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
              String name = (String) selectedObject;
              System.out.println(name);
              List<Movie> selection = Call.makeAPICall(name);
              new MovieGui(selection.get(0));
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
