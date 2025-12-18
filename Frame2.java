import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame2 extends JFrame {
    JList<Student> studentList;
    DefaultListModel<Student> listModel;
    JTextField filterField;
    JButton filterButton;
    JTextField minScoreField;
    JTextField maxScoreField;
    JButton scoreFilterButton;
    JComboBox<String> courseFilterCombo;
    JButton courseFilterButton;
    JComboBox<String> genderFilterCombo;
    JButton genderFilterButton;
    
    public Frame2() {
        setTitle("Student List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        StudentData.students.stream().forEach(s -> listModel.addElement(s));
        
        studentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentList);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(5, 1));
        
        // Name filter
        JPanel nameFilterPanel = new JPanel(new FlowLayout());
        JLabel filterLabel = new JLabel("Filter by Name:");
        nameFilterPanel.add(filterLabel);
        
        filterField = new JTextField(10);
        nameFilterPanel.add(filterField);
        
        filterButton = new JButton("Filter");
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filterText = filterField.getText();
                listModel.clear();
                StudentData.students.stream()
                    .filter(s -> s.name != null && (filterText.isEmpty() || s.name.contains(filterText)))
                    .forEach(s -> listModel.addElement(s));
            }
        });
        nameFilterPanel.add(filterButton);
        filterPanel.add(nameFilterPanel);
        
        // Score range filter
        JPanel scoreFilterPanel = new JPanel(new FlowLayout());
        JLabel scoreLabel = new JLabel("Score Range:");
        scoreFilterPanel.add(scoreLabel);
        
        minScoreField = new JTextField(5);
        scoreFilterPanel.add(minScoreField);
        
        JLabel toLabel = new JLabel("to");
        scoreFilterPanel.add(toLabel);
        
        maxScoreField = new JTextField(5);
        scoreFilterPanel.add(maxScoreField);
        
        scoreFilterButton = new JButton("Filter");
        scoreFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minScore = Integer.parseInt(minScoreField.getText());
                    int maxScore = Integer.parseInt(maxScoreField.getText());
                    listModel.clear();
                    StudentData.students.stream()
                        .filter(s -> s.score >= minScore && s.score <= maxScore)
                        .forEach(s -> listModel.addElement(s));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Frame2.this, "Please enter valid score range!");
                }
            }
        });
        scoreFilterPanel.add(scoreFilterButton);
        filterPanel.add(scoreFilterPanel);
        
        // Course filter
        JPanel courseFilterPanel = new JPanel(new FlowLayout());
        JLabel courseLabel = new JLabel("Filter by Course:");
        courseFilterPanel.add(courseLabel);
        
        String[] courses = {"All", "Math", "Calculus", "Physics", "Chemistry", "Biology"};
        courseFilterCombo = new JComboBox<>(courses);
        courseFilterPanel.add(courseFilterCombo);
        
        courseFilterButton = new JButton("Filter");
        courseFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseFilterCombo.getSelectedItem();
                listModel.clear();
                StudentData.students.stream()
                    .filter(s -> selectedCourse.equals("All") || selectedCourse.equals(s.course))
                    .forEach(s -> listModel.addElement(s));
            }
        });
        courseFilterPanel.add(courseFilterButton);
        filterPanel.add(courseFilterPanel);

        // Gender filter
        JPanel genderFilterPanel = new JPanel(new FlowLayout());
        JLabel genderLabel = new JLabel("Filter by Gender:");
        genderFilterPanel.add(genderLabel);

        String[] genders = {"All", "Male", "Female"};
        genderFilterCombo = new JComboBox<>(genders);
        genderFilterPanel.add(genderFilterCombo);

        genderFilterButton = new JButton("Filter");
        genderFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedGender = (String) genderFilterCombo.getSelectedItem();
                listModel.clear();
                StudentData.students.stream()
                    .filter(s -> selectedGender.equals("All") || selectedGender.equals(s.gender))
                    .forEach(s -> listModel.addElement(s));
            }
        });
        genderFilterPanel.add(genderFilterButton);
        filterPanel.add(genderFilterPanel);
        
        // Reset button
        JPanel resetPanel = new JPanel(new FlowLayout());
        JButton resetButton = new JButton("Reset All Filters");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterField.setText("");
                minScoreField.setText("");
                maxScoreField.setText("");
                courseFilterCombo.setSelectedIndex(0);
                genderFilterCombo.setSelectedIndex(0);
                listModel.clear();
                StudentData.students.stream().forEach(s -> listModel.addElement(s));
            }
        });
        resetPanel.add(resetButton);
        filterPanel.add(resetPanel);
        
        add(filterPanel, BorderLayout.NORTH);
    }
}
