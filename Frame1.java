import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame1 extends JFrame {
    JTextField nameField;
    JTextField scoreField;
    JSpinner ageSpinner;
    JComboBox<String> courseCombo;
    JRadioButton maleRadio;
    JRadioButton femaleRadio;
    ButtonGroup genderGroup;
    JButton addButton;
    JButton openFrame2Button;
    
    public Frame1() {
        setTitle("Add Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Name row
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(20);
        add(nameField, gbc);
        
        // Score row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel scoreLabel = new JLabel("Score:");
        add(scoreLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        scoreField = new JTextField(20);
        add(scoreField, gbc);
        
        // Course row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel courseLabel = new JLabel("Course:");
        add(courseLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        String[] courses = {"Math", "Calculus", "Physics", "Chemistry", "Biology"};
        courseCombo = new JComboBox<>(courses);
        add(courseCombo, gbc);
        
        // Age row
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel ageLabel = new JLabel("Age:");
        add(ageLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        SpinnerModel ageModel = new SpinnerNumberModel(18, 1, 100, 1);
        ageSpinner = new JSpinner(ageModel);
        add(ageSpinner, gbc);

        // Gender row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel genderLabel = new JLabel("Gender:");
        add(genderLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel, gbc);
        
        // Add button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String scoreText = scoreField.getText();
                String course = (String) courseCombo.getSelectedItem();
                int age = (Integer) ageSpinner.getValue();
                String gender = maleRadio.isSelected() ? "Male" : "Female";
                
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Frame1.this, "Please enter a name!");
                    return;
                }
                try {
                    int score = Integer.parseInt(scoreText);
                    Student s = new Student(name, score, course, age, gender);
                    StudentData.students.add(s);
                    JOptionPane.showMessageDialog(Frame1.this, "Student added!");
                    nameField.setText("");
                    scoreField.setText("");
                    ageSpinner.setValue(18);
                    maleRadio.setSelected(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Frame1.this, "Please enter a valid number for score!");
                }
            }
        });
        add(addButton, gbc);
        
        // View Students button
        gbc.gridy = 6;
        openFrame2Button = new JButton("View Students");
        openFrame2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame2 f2 = new Frame2();
                f2.setVisible(true);
            }
        });
        add(openFrame2Button, gbc);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Frame1();
    }
}
