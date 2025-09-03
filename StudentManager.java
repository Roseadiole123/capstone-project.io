package capstone.core;

import java.util.*;
import javax.swing.JOptionPane;

public class StudentManager {
    private ArrayList<Studentinfo> students = new ArrayList<>();

    // Add student
    public void addStudent(Studentinfo s) {
        try {
            if (s.getCgpa() > 4.5) {
                JOptionPane.showMessageDialog(null, "Error: Your Cgpa cannot be greater than 4.5 naa!");
                return; // stop execution
            }
            students.add(s);
            JOptionPane.showMessageDialog(null, "Ehen! Student is added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
        }
    }

    // Search student by ID (Linear Search)
    public Studentinfo searchByRegnumber(int Regnumber) {
        for (Studentinfo s : students) {
            if (s.getRegnumber() == Regnumber) {
                return s;
            }
        }
        return null;
    }

    // Sort students by CGPA (Bubble Sort Example)
    public void sortByCgpa() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getCgpa() < students.get(j + 1).getCgpa()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
    }

    // Sort students by Name (QuickSort)
    public void sortByName() {
        quickSort(students, 0, students.size() - 1);
    }

    private void quickSort(ArrayList<Studentinfo> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(ArrayList<Studentinfo> list, int low, int high) {
        String pivot = list.get(high).getName().toLowerCase();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getName().toLowerCase().compareTo(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // Display students
    public void displayStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students are up for display!");
        } else {
            StringBuilder sb = new StringBuilder("Student List:\n");
            for (Studentinfo s : students) {
                sb.append("Name: ")
                        .append(s.getName())
                        .append(", Regnumber: ")
                        .append(s.getRegnumber())
                        .append(", CGPA: ")
                        .append(s.getCgpa())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    // Getter
    public ArrayList<Studentinfo> getStudents() {
        return students;
    }
}
