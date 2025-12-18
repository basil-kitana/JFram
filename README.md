# JFram

A simple Java Swing application for managing student records.

## Description

This is a basic student management application with two frames:
- **Frame1**: Add student names and scores using text fields and buttons
- **Frame2**: Display students in a JList with a basic filter functionality

## How to Run

1. Compile the Java files:
   ```bash
   javac *.java
   ```

2. Run the application:
   ```bash
   java Frame1
   ```

## Usage

1. When Frame1 opens, enter a student name and score, then click "Add Student"
2. Click "View Students" to open Frame2 and see the list of students
3. In Frame2, use the filter field to search for students by name
4. Click "Reset" to clear the filter and show all students

## Files

- `Frame1.java` - Main frame for adding students
- `Frame2.java` - Display frame with student list and filter
- `Student.java` - Student data class
- `StudentData.java` - Shared data storage between frames