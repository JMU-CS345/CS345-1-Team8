package IMDBProject;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * GUI for displaying search results.
 *
 * @author Erin Crowley
 * @author Chase Coleman
 * @author Cal Fitzgerald
 * @author Zoe Zinn
 */
public class SearchResultsGui extends JFrame {
  private JFrame main;
  private JTree tree;
  private JScrollPane treeView;
  private JPanel displayPanel;
  private ArrayList<Movie> movies;

  /**
   * GUI constructor.
   *
   * @param movies the list of movie to display
   */
  public SearchResultsGui(ArrayList<Movie> movies) {
    this.main = this;
    this.movies = movies;
    buildGui();
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
   * Helper method for building the contents.
   */
  private void buildContents() {
    buildTree();
    displayPanel.add(getTreeView());
  }

  /**
   * Helper method for creating the display panel.
   *
   * @return a Jpanel of the display
   */
  private Component getDisplayPanel() {
    this.displayPanel = new JPanel();
    displayPanel.setPreferredSize(new Dimension(400, 500));
    buildContents();
    return displayPanel;
  }

  /**
   * Build the Jtree for the user to navigate.
   */
  private void buildTree() {
    tree = new JTree();
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
    for (Movie movie : movies) {
      rootNode.add(new DefaultMutableTreeNode(movie));
    }
    TreeModel model = new DefaultTreeModel(rootNode);
    tree.setModel(model);
    tree.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
    tree.setRootVisible(false);
  }

  /**
   * Edit Jtree to display information about the selected movie.
   *
   * @return a scrollable Jtree to the panel
   */
  private Component getTreeView() {

    // Change tree icon
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

    treeView = new JScrollPane(tree);

    tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Object selectedNode = e.getPath().getLastPathComponent();
        if (selectedNode instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
          Object selectedObject = node.getUserObject();
          if (selectedObject instanceof Movie) {
            Movie movie = (Movie) selectedObject;
            try {
              new MovieGui((Movie) selectedObject);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
          }
        }
      }
    });
    return treeView;
  }
}
