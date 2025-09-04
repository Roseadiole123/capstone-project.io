# capstone-project.io
Smart Student Platform – User Manual

1. Introduction

Smart Student Platform is a Java Swing desktop application for managing student records and academic results.
It allows administrators to add/update student details, search and sort records, and compute class performance statistics.

2. System Requirements

Java JDK 8+ installed on your system

Operating System: Windows, Linux, or macOS

A desktop environment (Swing requires a GUI, not headless server)

3. Installation

Download or clone the project from the repository.

git clone https://github.com/xamvi12/capstone-project.io.git

Change Directory:
cd capstone-project.io

Compile the Code
javac -d . *.java

Package into a JAR
jar cfe SmartStudentPlatform.jar 
capstone.core.CapstoneCore capstone/core/*.class

Run the Application
java -jar SmartStudentPlatform.jar
👉 The Swing GUI will open.

So I created a **helper script run.sh**
sudo nano run.sh

Ensure run.sh is executable:

chmod +x run.sh

4. Running the Application

Compile and run everything in one step:

./run.sh all


Run using the JAR (after building):

java -jar SmartStudentPlatform.jar

<img width="667" height="242" alt="image" src="https://github.com/user-attachments/assets/593c88f7-b458-4c97-ab6f-2b4833fa4b91" />


5) How to use the app 

Add or update a student: enter ID and Name at the top, click “Add/Update Student”.

Add a course result to a selected student:

Click the student row in the table.

Fill Course Code, Title, Units (number), and Score (0–100), click “Add Result…”.

Sort:

Choose “Sort by Name (QuickSort)” or “Sort by CGPA (Bubble Sort)” in the dropdown.

Search:

Type an ID in “Search by ID”, click “Linear Search” or “Binary Search”.

Summaries:

The bar at the bottom shows Class Average CGPA and Top Performer.

Save/Load:

Click “Save” to write all data to a file, “Load” to restore from a file.

Errors:

If you enter text for a number, or leave fields empty, you’ll see a clear error popup (JOptionPane).

6) How this meets the rubric and requirements

Object-Oriented Programming

Classes: Student, Course, Result. Encapsulation via private fields + getters/setters. Abstraction via Identifiable interface and StudentManager facade for data operations.

Overloading/Overriding: Result.setScore recalculates grade/points; toString overridden in Student.

Collections & Algorithms

Data structures: ArrayList (ordered list), HashMap (fast lookup by ID) used in StudentManager.

Sorting: QuickSort by name (custom), Bubble Sort by CGPA (custom).

Searching: Linear search and Binary search by ID (binary uses a sorted copy).

GUI & Event Handling (Swing)

Components: JFrame, JTable, JTextField, JComboBox, JButton, JOptionPane.

Listeners: ActionListener on buttons, ItemListener on the sort dropdown.

Display: JTable via a custom AbstractTableModel.

Exception Handling

try/catch around numeric parsing, empty fields, invalid ranges, and file I/O.

Error feedback with JOptionPane messages.

Save/Load

Simple Java serialization of StudentManager to a file (and back).

![WhatsApp Image 2025-09-04 at 07 47 42_501ee7d8](https://github.com/user-attachments/assets/878ba02f-70ef-41d7-85d9-f29a591de7ba)

![WhatsApp Image 2025-09-04 at 07 50 10_1be4764a](https://github.com/user-attachments/assets/468e3a49-1fc4-498c-aecc-04e01d182cb8)

![WhatsApp Image 2025-09-04 at 07 54 02_385489ae](https://github.com/user-attachments/assets/4d0cda4f-ada1-40b8-b137-f773b7131e94)



7. Features

Add Student: Enter ID, name, CGPA, and enrolled courses.

Update Student: Select an existing student and modify details.

View Students: Display all records in a sortable table/list.

Sort Records: By Name (QuickSort), by CGPA (Bubble Sort), or by ID.

Search Students: By ID (Linear or Binary Search).

Compute Results: Show class average, highest CGPA, and top performer.

Save/Load: Persist data to a file and reload later.

8. Error Handling

Invalid inputs (non-numeric CGPA, empty fields) trigger error popups via JOptionPane.

Missing files or corrupt data will display user-friendly error messages.

9. Closing the App

![WhatsApp Image 2025-09-04 at 07 57 50_132dbb7d](https://github.com/user-attachments/assets/3c2577bc-25bf-4f2b-960a-fb4031c181a9)

Click the Exit button or close the window from the GUI.
