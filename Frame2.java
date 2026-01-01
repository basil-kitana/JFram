import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.sql.SQLException;

public class Frame2 extends JFrame {
    JList<Student> l;
    DefaultListModel<Student> dlm;
    JTextField f1, f2, f3;
    JButton b1, b2, b3, b4, b5, b6;
    JComboBox<String> c1, c2;
    DataContext d = new DataContext();
    ArrayList<Student> al = new ArrayList<>();

    public Frame2() {
        setTitle("Students");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        dlm = new DefaultListModel<>();
        load();

        l = new JList<>(dlm);
        add(new JScrollPane(l), BorderLayout.CENTER);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 1));
        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(new JLabel("Name:"));
        f1 = new JTextField(10);
        p2.add(f1);

        b1 = new JButton("Go");
        b1.addActionListener(e -> {
            String t = f1.getText();
            dlm.clear();
            al.stream().filter(s -> s.name != null && (t.isEmpty() || s.name.contains(t)))
                    .forEach(s -> dlm.addElement(s));
        });
        p2.add(b1);
        p1.add(p2);

        JPanel p3 = new JPanel(new FlowLayout());
        p3.add(new JLabel("Score:"));
        f2 = new JTextField(5);
        p3.add(f2);

        p3.add(new JLabel("to"));
        f3 = new JTextField(5);
        p3.add(f3);

        b2 = new JButton("Go");
        b2.addActionListener(e -> {
            try {
                int min = Integer.parseInt(f2.getText());
                int max = Integer.parseInt(f3.getText());
                dlm.clear();
                al.stream().filter(s -> s.score >= min && s.score <= max).forEach(s -> dlm.addElement(s));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Frame2.this, "Error");
            }
        });
        p3.add(b2);
        p1.add(p3);

        JPanel p4 = new JPanel(new FlowLayout());
        p4.add(new JLabel("Course:"));
        c1 = new JComboBox<>(new String[] { "All", "Math", "Calculus", "Physics", "Chemistry", "Biology" });
        p4.add(c1);
        b3 = new JButton("Go");
        b3.addActionListener(e -> {
            String sc = (String) c1.getSelectedItem();
            dlm.clear();
            al.stream().filter(s -> sc.equals("All") || sc.equals(s.course)).forEach(s -> dlm.addElement(s));
        });
        p4.add(b3);
        p1.add(p4);

        JPanel p5 = new JPanel(new FlowLayout());
        p5.add(new JLabel("Gender:"));
        c2 = new JComboBox<>(new String[] { "All", "Male", "Female" });
        p5.add(c2);

        b4 = new JButton("Go");
        b4.addActionListener(e -> {
            String sg = (String) c2.getSelectedItem();
            dlm.clear();
            al.stream().filter(s -> sg.equals("All") || sg.equals(s.gender)).forEach(s -> dlm.addElement(s));
        });
        p5.add(b4);
        p1.add(p5);

        JPanel p6 = new JPanel(new FlowLayout());
        JButton r = new JButton("Reset");
        r.addActionListener(e -> {
            f1.setText("");
            f2.setText("");
            f3.setText("");
            c1.setSelectedIndex(0);
            c2.setSelectedIndex(0);
            dlm.clear();
            al.forEach(s -> dlm.addElement(s));
        });
        p6.add(r);
        p1.add(p6);

        add(p1, BorderLayout.NORTH);
        JPanel p7 = new JPanel();
        b5 = new JButton("Delete");
        b5.addActionListener(e -> {
            Student s = l.getSelectedValue();
            if (s == null)
                return;
            if (JOptionPane.showConfirmDialog(Frame2.this, "Delete " + s.name + "?") == JOptionPane.YES_OPTION) {
                try {
                    d.deleteStudent(s.id);
                    al.remove(s);
                    dlm.removeElement(s);
                } catch (SQLException ex) {
                }
            }
        });
        p7.add(b5);
        b6 = new JButton("Stats");
        b6.addActionListener(e -> stat());
        p7.add(b6);
        add(p7, BorderLayout.SOUTH);
    }

    private void load() {
        try {
            al = d.getStudents();
            dlm.clear();
            for (Student s : al)
                dlm.addElement(s);
        } catch (SQLException ex) {
        }
    }

    private void stat() {
        if (al.isEmpty())
            return;
        double avg = al.stream().mapToInt(s -> s.score).average().orElse(0);
        int age = (int) al.stream().mapToInt(s -> s.age).average().orElse(0);
        long m = al.stream().filter(s -> "Male".equalsIgnoreCase(s.gender)).count();
        long f = al.stream().filter(s -> "Female".equalsIgnoreCase(s.gender)).count();
        JOptionPane.showMessageDialog(this, "Count: " + al.size() + "\nAvg Score: " + avg + "\nAvg Age: " + age
                + "\nMale: " + m + "\nFemale: " + f);
    }
}
