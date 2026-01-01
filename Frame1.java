import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;

public class Frame1 extends JFrame {
  JTextField t1, t2;
  JSpinner s1;
  JComboBox<String> c1;
  JRadioButton r1, r2;
  ButtonGroup bg;
  JButton b1, b2;
  DataContext d = new DataContext();

  public Frame1() {
    setTitle("Add");
    setSize(400, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(5, 5, 5, 5);
    g.fill = GridBagConstraints.HORIZONTAL;
    g.gridx = 0;
    g.gridy = 0;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel("Name:"), g);

    g.gridx = 1;
    g.anchor = GridBagConstraints.WEST;
    t1 = new JTextField(20);
    add(t1, g);

    g.gridx = 0;
    g.gridy = 1;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel("Score:"), g);
    g.gridx = 1;
    g.anchor = GridBagConstraints.WEST;
    t2 = new JTextField(20);
    add(t2, g);
    g.gridx = 0;
    g.gridy = 2;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel("Course:"), g);

    g.gridx = 1;
    g.anchor = GridBagConstraints.WEST;
    c1 = new JComboBox<>(new String[] { "Math", "Calculus", "Physics", "Chemistry", "Biology" });
    add(c1, g);
    g.gridx = 0;
    g.gridy = 3;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel("Age:"), g);
    g.gridx = 1;
    g.anchor = GridBagConstraints.WEST;
    s1 = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
    add(s1, g);

    g.gridx = 0;
    g.gridy = 4;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel("Gender:"), g);
    g.gridx = 1;
    g.anchor = GridBagConstraints.WEST;
    JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    r1 = new JRadioButton("Male");
    r2 = new JRadioButton("Female");
    bg = new ButtonGroup();
    bg.add(r1);
    bg.add(r2);
    r1.setSelected(true);
    p.add(r1);
    p.add(r2);
    add(p, g);

    g.gridx = 0;
    g.gridy = 5;
    g.gridwidth = 2;
    g.anchor = GridBagConstraints.CENTER;
    b1 = new JButton("Add Student");
    b1.addActionListener(e -> {
      String n = t1.getText();
      String sc = t2.getText();
      String co = (String) c1.getSelectedItem();
      int a = (Integer) s1.getValue();
      String gn = r1.isSelected() ? "Male" : "Female";
      if (n == null || n.trim().isEmpty()) {
        JOptionPane.showMessageDialog(Frame1.this, "Name?");
        return;
      }
      try {
        int s = Integer.parseInt(sc);
        d.saveStudent(new Student(n, s, co, a, gn));
        JOptionPane.showMessageDialog(Frame1.this, "Added");
        t1.setText("");
        t2.setText("");
        s1.setValue(18);
        r1.setSelected(true);
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(Frame1.this, "SQL Error");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(Frame1.this, "Error");
      }
    });
    add(b1, g);

    g.gridy = 6;
    b2 = new JButton("View");
    b2.addActionListener(e -> new Frame2().setVisible(true));
    add(b2, g);
    setVisible(true);
  }

  public static void main(String[] a) {
    new Frame1();
  }
}
