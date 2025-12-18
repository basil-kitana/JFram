import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame1 extends JFrame {
    JTextField nameField;
    JTextField scoreField;
    JButton addButton;
    JButton openFrame2Button;
    
    public Frame1() {
        setTitle("Add Student");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel);
        
        nameField = new JTextField(15);
        add(nameField);
        
        JLabel scoreLabel = new JLabel("Score:");
        add(scoreLabel);
        
        scoreField = new JTextField(15);
        add(scoreField);
        
        addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String scoreText = scoreField.getText();
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Frame1.this, "Please enter a name!");
                    return;
                }
                try {
                    int score = Integer.parseInt(scoreText);
                    Student s = new Student(name, score);
                    StudentData.students.add(s);
                    JOptionPane.showMessageDialog(Frame1.this, "Student added!");
                    nameField.setText("");
                    scoreField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Frame1.this, "Please enter valid data!");
                }
            }
        });
        add(addButton);
        
        openFrame2Button = new JButton("View Students");
        openFrame2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame2 f2 = new Frame2();
                f2.setVisible(true);
            }
        });
        add(openFrame2Button);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Frame1();
    }
}
