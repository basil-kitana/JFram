# JFram

A simple Java Swing application for managing student records.

## Description

This is a basic student management application with two frames:
- **Frame1**: Add student names, scores, course (dropdown), and age (spinner) using a clean grid layout
- **Frame2**: Display students in a JList with multiple filter options

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

1. When Frame1 opens, enter:
   - Student name (text field)
   - Score (integer)
   - Course (dropdown: Math, Calculus, Physics, Chemistry, Biology)
   - Age (spinner with up/down arrows, integer only)
   Then click "Add Student"
   
2. Click "View Students" to open Frame2 and see the list of students

3. In Frame2, use multiple filter options:
   - **Filter by Name**: Search for students by name
   - **Score Range**: Filter students by score range (min to max)
   - **Filter by Course**: Filter students by course
   - Click "Reset All Filters" to clear all filters and show all students

## Files

- `Frame1.java` - Main frame for adding students with improved GridBagLayout
- `Frame2.java` - Display frame with student list and multiple filters
- `Student.java` - Student data class with name, score, course, and age
- `StudentData.java` - Shared data storage between frames