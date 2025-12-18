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
    JComboBox<String> gradeFilterCombo;
    JButton gradeFilterButton;
    
    public Frame2() {
        setTitle("Student List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        listModel = new DefaultListModel<>();
        for (Student s : StudentData.students) {
            listModel.addElement(s);
        }
        
        studentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentList);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(4, 1));
        
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
                for (Student s : StudentData.students) {
                    if (s.name != null && (filterText.isEmpty() || s.name.contains(filterText))) {
                        listModel.addElement(s);
                    }
                }
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
                    for (Student s : StudentData.students) {
                        if (s.score >= minScore && s.score <= maxScore) {
                            listModel.addElement(s);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Frame2.this, "Please enter valid score range!");
                }
            }
        });
        scoreFilterPanel.add(scoreFilterButton);
        filterPanel.add(scoreFilterPanel);
        
        // Grade filter
        JPanel gradeFilterPanel = new JPanel(new FlowLayout());
        JLabel gradeLabel = new JLabel("Filter by Grade:");
        gradeFilterPanel.add(gradeLabel);
        
        String[] grades = {"All", "Freshman", "Sophomore", "Junior", "Senior"};
        gradeFilterCombo = new JComboBox<>(grades);
        gradeFilterPanel.add(gradeFilterCombo);
        
        gradeFilterButton = new JButton("Filter");
        gradeFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedGrade = (String) gradeFilterCombo.getSelectedItem();
                listModel.clear();
                for (Student s : StudentData.students) {
                    if (selectedGrade.equals("All") || s.grade.equals(selectedGrade)) {
                        listModel.addElement(s);
                    }
                }
            }
        });
        gradeFilterPanel.add(gradeFilterButton);
        filterPanel.add(gradeFilterPanel);
        
        // Reset button
        JPanel resetPanel = new JPanel(new FlowLayout());
        JButton resetButton = new JButton("Reset All Filters");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterField.setText("");
                minScoreField.setText("");
                maxScoreField.setText("");
                gradeFilterCombo.setSelectedIndex(0);
                listModel.clear();
                for (Student s : StudentData.students) {
                    listModel.addElement(s);
                }
            }
        });
        resetPanel.add(resetButton);
        filterPanel.add(resetPanel);
        
        add(filterPanel, BorderLayout.NORTH);
    }
}
