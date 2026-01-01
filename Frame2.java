import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.SQLException;

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
    JButton deleteButton; // New Feature
    JButton statsButton; // New Feature

    DataContext db = new DataContext();
    ArrayList<Student> allStudents = new ArrayList<>();

    public Frame2() {
        setTitle("Student List");
        setSize(500, 500); // Increased height for new buttons
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        loadStudents();

        studentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(studentList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(6, 1)); // Increased rows

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
                allStudents.stream()
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
                    allStudents.stream()
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

        String[] courses = { "All", "Math", "Calculus", "Physics", "Chemistry", "Biology" };
        courseFilterCombo = new JComboBox<>(courses);
        courseFilterPanel.add(courseFilterCombo);

        courseFilterButton = new JButton("Filter");
        courseFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseFilterCombo.getSelectedItem();
                listModel.clear();
                allStudents.stream()
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

        String[] genders = { "All", "Male", "Female" };
        genderFilterCombo = new JComboBox<>(genders);
        genderFilterPanel.add(genderFilterCombo);

        genderFilterButton = new JButton("Filter");
        genderFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedGender = (String) genderFilterCombo.getSelectedItem();
                listModel.clear();
                allStudents.stream()
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
                allStudents.stream().forEach(s -> listModel.addElement(s));
            }
        });
        resetPanel.add(resetButton);
        filterPanel.add(resetPanel);

        add(filterPanel, BorderLayout.NORTH);

        // Action Panel (Delete & Stats)
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student selected = studentList.getSelectedValue();
                if (selected == null) {
                    JOptionPane.showMessageDialog(Frame2.this, "Please select a student to delete.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(Frame2.this,
                        "Are you sure you want to delete " + selected.name + "?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        db.deleteStudent(selected.id);
                        allStudents.remove(selected);
                        listModel.removeElement(selected);
                        JOptionPane.showMessageDialog(Frame2.this, "Student deleted.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Frame2.this, "Error deleting student: " + ex.getMessage());
                    }
                }
            }
        });
        actionPanel.add(deleteButton);

        statsButton = new JButton("View Statistics");
        statsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showStatistics();
            }
        });
        actionPanel.add(statsButton);

        add(actionPanel, BorderLayout.SOUTH);
    }

    private void loadStudents() {
        try {
            allStudents = db.getStudents();
            listModel.clear();
            for (Student s : allStudents) {
                listModel.addElement(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
    }

    private void showStatistics() {
        if (allStudents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data available.");
            return;
        }

        double avgScore = allStudents.stream().mapToInt(s -> s.score).average().orElse(0);
        int avgAge = (int) allStudents.stream().mapToInt(s -> s.age).average().orElse(0);
        long maleCount = allStudents.stream().filter(s -> "Male".equalsIgnoreCase(s.gender)).count();
        long femaleCount = allStudents.stream().filter(s -> "Female".equalsIgnoreCase(s.gender)).count();

        String msg = String.format("Total Students: %d\nAverage Score: %.2f\nAverage Age: %d\nMale: %d\nFemale: %d",
                allStudents.size(), avgScore, avgAge, maleCount, femaleCount);

        JOptionPane.showMessageDialog(this, msg, "Class Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}
