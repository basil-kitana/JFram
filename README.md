# JFram

A simple Java Swing application for managing student records.

## Description

This is a basic student management application with two frames:
- **Frame1**: Add student details (name, score, course, age, gender) using a clean `GridBagLayout` form
- **Frame2**: Display students in a `JList` with multiple filter options

Data is stored in a MySQL database via `DataContext.java`.

## Features

- **MySQL Database Integration**: Persistent storage for student records
- Add students with: name, score, course, age, and gender
- Course selection via dropdown (Math, Calculus, Physics, Chemistry, Biology)
- Age selection via `JSpinner` (range 1-100, default 18)
- View students in a list (one line per student)
- **Delete Functionality**: Remove students from the database
- **View Statistics**: See class averages and gender breakdown
- Filters in the list view:
  - Name contains (substring match)
  - Score range (min - max)
  - Course (or All)
  - Gender (or All)

## How to Run

### Prerequisites

- Java JDK installed (so `javac` and `java` are available)
- MySQL Server installed and running locally
  - Database name: `company`
  - User: `root`
  - Password: (empty)
  - Port: `3306`

### Steps

1. Run the automation script (PowerShell):
   ```powershell
   ./run.ps1
   ```
   
   This script will automatically:
   - Find the MySQL connector jar in the `lib` folder
   - Compile all Java files
   - Run the application

   *Note: If you run into execution policy errors, you might need to run `Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass` first.*

## Usage

1. When Frame1 opens, enter:
   - Student name (text field)
   - Score (integer)
   - Course (dropdown: Math, Calculus, Physics, Chemistry, Biology)
   - Age (spinner with up/down arrows, integer only)
   - Gender (radio buttons: Male/Female)
   Then click "Add Student"
   
2. Click "View Students" to open Frame2 and see the list of students

3. In Frame2, use multiple filter options:
   - **Filter by Name**: Search for students by name
   - **Score Range**: Filter students by score range (min to max)
   - **Filter by Course**: Filter students by course
   - **Filter by Gender**: Filter students by gender
   - **Delete Selected**: Select a student and click to delete
   - **View Statistics**: View class analytics
   - Click "Reset All Filters" to clear all filters and show all students

## Files

- `Frame1.java` - Main frame for adding students with improved GridBagLayout
- `Frame2.java` - Display frame with student list, filters, delete, and statistics
- `DataContext.java` - Handles MySQL database connection and CRUD operations
- `Student.java` - Student data class (name, score, course, age, gender)
- `StudentData.java` - Legacy in-memory storage (mostly unused in favor of DataContext)