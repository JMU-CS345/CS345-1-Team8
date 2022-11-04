package IMDBProject;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * GUI for displaying search results.
 *
 * @author Erin Crowley
 * @author Chase
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
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Helper method for building the GUI.
   */
  private void buildGui() {
    buildTree();
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
      String title = movie.getTitle();
      rootNode.add(new DefaultMutableTreeNode(title));
    }
    TreeModel model = new DefaultTreeModel(rootNode);
    tree.setModel(model);
    tree.setRootVisible(false);
  }

  /**
   * Edit Jtree to display information about the selected movie.
   *
   * @return a scrollable Jtree to the panel
   */
  private Component getTreeView() {
    treeView = new JScrollPane(tree);

    tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        Object selectedNode = e.getPath().getLastPathComponent();
        if (selectedNode instanceof DefaultMutableTreeNode) {
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
          Object selectedObject = node.getUserObject();
        }
      }
    });
    return treeView;
  }
}
