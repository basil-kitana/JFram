import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame2 extends JFrame {
    JList<Student> studentList;
    DefaultListModel<Student> listModel;
    JTextField filterField;
    JButton filterButton;
    
    public Frame2() {
        setTitle("Student List");
        setSize(400, 300);
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
        filterPanel.setLayout(new FlowLayout());
        
        JLabel filterLabel = new JLabel("Filter:");
        filterPanel.add(filterLabel);
        
        filterField = new JTextField(15);
        filterPanel.add(filterField);
        
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
        filterPanel.add(filterButton);
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterField.setText("");
                listModel.clear();
                for (Student s : StudentData.students) {
                    listModel.addElement(s);
                }
            }
        });
        filterPanel.add(resetButton);
        
        add(filterPanel, BorderLayout.NORTH);
    }
}
