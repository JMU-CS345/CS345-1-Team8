package IMDBProject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.*;

public class Leo extends JFrame {

  public static void main(String[] args) throws MalformedURLException {
    new Leo();
  }

  private Box box;
  private JTextField input;

  public Leo() throws MalformedURLException {
    GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = graphics.getDefaultScreenDevice();
    buildGui();
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    device.setFullScreenWindow(this);
  }

  public Leo(int age) throws MalformedURLException {
    GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = graphics.getDefaultScreenDevice();
    if (age < 18) {
      buildYoung();
    } else if (age > 25) {
      buildOld();
    } else {
      buildPerfect();
    }
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    device.setFullScreenWindow(this);
  }

  private void buildGui() throws MalformedURLException {
    this.getContentPane().setLayout(new BorderLayout());
    this.box = new Box(BoxLayout.Y_AXIS);
    ImageIcon pic = new ImageIcon(new URL("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg"));
    JLabel label = new JLabel(pic);
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    box.add(label);
    box.add(getText("Hey..."));
    box.add(getText("I saw you coding from across the classroom and thought I'd introduce myself"));
    box.add(getText("What's your sign btw? I'm a leo"));
    box.add(getText("No but seriously how old are you?"));
    box.add(getInput());
    box.add(createSubmitButton());
    this.getContentPane().add(box);
    this.getContentPane().setBackground(new Color(25, 0, 0));
  }

  private JLabel getText(String words) {
    JLabel text = new JLabel(words);
    text.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
    text.setForeground(Color.WHITE);
    text.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    return text;
  }

  private JTextField getInput() {
    this.input = new JTextField("                                                                      Enter age:");
    input.setBackground(new Color(25, 0, 0));
    input.setBorder(new LineBorder(new Color(25, 0, 0)));
    input.setPreferredSize(new Dimension(100, 25));
    input.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
    input.setForeground(Color.WHITE);
    input.setBounds(300, 0,0,0);

    return input;
  }

  private JButton createSubmitButton() {
    JButton submit = new JButton("Submit");
    submit.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
    // Action listener for when the button is clicked
    submit.addActionListener(e -> {
      int age = Integer.parseInt(input.getText().substring(input.getText().indexOf(":") + 1));
      try {
        new Leo(age);
      } catch (MalformedURLException ex) {
        throw new RuntimeException(ex);
      }
      System.out.println(age);
    });
    return submit;
  }

  private void buildYoung() throws MalformedURLException {
    this.getContentPane().setLayout(new BorderLayout());
    this.box = new Box(BoxLayout.Y_AXIS);
    JLabel label = new JLabel("(the wild Leo fled)");
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
    label.setForeground(Color.white);
    box.add(label);
    this.setVisible(true);
    this.getContentPane().add(box);
    this.getContentPane().setBackground(new Color(25, 0, 0));
  }

  private void buildOld() throws MalformedURLException {
    this.getContentPane().setLayout(new BorderLayout());
    this.box = new Box(BoxLayout.Y_AXIS);
    ImageIcon link = new ImageIcon(new URL("https://hips.hearstapps.com/del.h-cdn.co/assets/16/08/4000x2662/gallery-1456417138-gettyimages-505545208.jpg?resize=980:*"));
    JLabel pic = new JLabel(link);
    pic.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    box.add(pic);
    JLabel label = new JLabel("Do you have a daughter perhaps?");
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
    label.setForeground(Color.white);
    box.add(label);
    this.setVisible(true);
    this.getContentPane().add(box);
    this.getContentPane().setBackground(new Color(25, 0, 0));
  }

  private void buildPerfect() throws MalformedURLException {
    this.getContentPane().setLayout(new BorderLayout());
    this.box = new Box(BoxLayout.Y_AXIS);
    ImageIcon link = new ImageIcon(new URL("https://www.awesomebox.com/assets/public_box/leonardodicaprio/facebook.jpg"));
    JLabel pic = new JLabel(link);
    pic.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    box.add(pic);
    JLabel label = new JLabel("say less ;)");
    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
    label.setForeground(Color.white);
    box.add(label);
    this.setVisible(true);
    this.getContentPane().add(box);
    this.getContentPane().setBackground(new Color(25, 0, 0));
  }
}
