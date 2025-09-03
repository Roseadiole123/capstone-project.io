package capstone.core;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class CapstoneCore extends JFrame {
    private StudentManager manager = new StudentManager();
    private DefaultTableModel model;
    
    private JTextField nameField;
    private JTextField regNumberField;
    private JTextField cgpaField;

    public CapstoneCore() {
        setTitle("Student Platform By Group 1");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Theme colors
        Color backgroundBlack = new Color(20, 20, 20); 
        Color accentGreen = new Color(0, 200, 0);
        Color buttonGreen = new Color(34, 139, 34);
        Color textWhite = Color.WHITE;
        
        
        // Table
        model = new DefaultTableModel(new String[]{"Name", "Reg Number", "CGPA"}, 0);
        JTable table = new JTable(model);
        table.setBackground(backgroundBlack);
        table.setForeground(textWhite);
        table.setSelectionBackground(accentGreen);
        table.setSelectionForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setBackground(buttonGreen);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(backgroundBlack);

        // Inputs
        nameField = new JTextField(10);
        regNumberField = new JTextField(10);
        cgpaField = new JTextField(10);
         
          // 🎨 Style input fields (black bg, green text, white caret)
        JTextField[] inputs = {nameField, regNumberField, cgpaField};
        for (JTextField input : inputs) {
        input.setBackground(backgroundBlack);
        input.setForeground(accentGreen);
        input.setCaretColor(Color.WHITE); // blinking cursor
        input.setFont(new Font("Arial", Font.PLAIN, 13));
        input.setBorder(BorderFactory.createLineBorder(accentGreen)); // green border
}

        // Buttons
        JButton addBtn = new JButton("Add Student");
        JButton sortNameBtn = new JButton("Sort Name");
        JButton sortCgpaBtn = new JButton("Sort CGPA");
        JButton searchBtn = new JButton("Search RegNumber");
        
        JButton[] buttons = {addBtn, sortNameBtn, sortCgpaBtn, searchBtn};
        for (JButton btn : buttons) {
            btn.setBackground(buttonGreen);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
        }

        // Panel (top bar)
        JPanel panel = new JPanel();
        panel.setBackground(backgroundBlack);
        panel.setForeground(textWhite);

        JLabel title = new JLabel("Student Platform By Group 1");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(accentGreen);

        panel.add(title);
        panel.add(new JLabel("   ")); // small spacing
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(accentGreen);
        JLabel regLabel = new JLabel("Reg No:");
        regLabel.setForeground(accentGreen);
        JLabel cgpaLabel = new JLabel("CGPA:");
        cgpaLabel.setForeground(accentGreen);

        
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Reg No:"));
        panel.add(regNumberField);
        panel.add(new JLabel("CGPA:"));
        panel.add(cgpaField);
        panel.add(addBtn);
        panel.add(sortNameBtn);
        panel.add(sortCgpaBtn);
        panel.add(searchBtn);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button Actions

        // Add Student
        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int reg = Integer.parseInt(regNumberField.getText());
                double cgpa = Double.parseDouble(cgpaField.getText()); // ✅ decimal allowed

                Studentinfo s = new Studentinfo(reg, name, cgpa);
                manager.addStudent(s);

                // Only add to table if student was accepted
                if (s.getCgpa() <= 4.5 && s.getCgpa() >= 0) {
                    model.addRow(new Object[]{name, reg, cgpa});
                }

                nameField.setText("");
                regNumberField.setText("");
                cgpaField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid input! RegNumber must be an integer and CGPA must be a number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sort by Name
        sortNameBtn.addActionListener(e -> {
            manager.sortByName();
            refreshTable();
        });

        // Sort by CGPA
        sortCgpaBtn.addActionListener(e -> {
            manager.sortByCgpa();
            refreshTable();
        });

        // Search by RegNumber
        searchBtn.addActionListener(e -> {
            try {
                int target = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Reg Number:"));
                Studentinfo found = manager.searchByRegnumber(target);

                if (found != null) {
                    JOptionPane.showMessageDialog(this, "Found: " + found.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid RegNumber!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Refresh table
    private void refreshTable() {
        model.setRowCount(0);
        for (Studentinfo s : manager.getStudents()) {
            model.addRow(new Object[]{s.getName(), s.getRegnumber(), s.getCgpa()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CapstoneCore().setVisible(true));
    }
}
