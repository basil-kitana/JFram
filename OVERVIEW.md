# Application Overview

## Application Structure

```
Frame1 (Main Frame) - Using GridBagLayout
├── Name TextField
├── Score TextField (integer)
├── Course ComboBox (dropdown: Math, Calculus, Physics, Chemistry, Biology)
├── Age JSpinner (integer with up/down arrows)
├── Gender Radio Buttons (Male/Female)
├── Add Student Button
│   └── Creates Student object with all fields
│       └── Saves to MySQL Database
└── View Students Button
    └── Opens Frame2

Frame2 (Display Frame)
├── JList (shows all students)
├── Filter by Name
│   ├── TextField for name search
│   └── Filter Button
├── Filter by Score Range
│   ├── Min Score TextField
│   ├── Max Score TextField
│   └── Filter Button
├── Filter by Course
│   ├── Course ComboBox (dropdown with All/Math/Calculus/Physics/Chemistry/Biology)
│   └── Filter Button
├── Filter by Gender
│   ├── Gender ComboBox (dropdown with All/Male/Female)
│   └── Filter Button
├── Reset All Filters Button
│   └── Shows all students again
├── Delete Selected Button
│   └── Removes selected student from Database and List
└── View Statistics Button
    └── Shows popup with Class Statistics (Avg Score, Age, Gender count)
```

## Data Flow

1. User enters student data in Frame1:
   - Name (text)
   - Score (integer)
   - Course (selected from dropdown)
   - Age (integer using spinner)
   - Gender (radio buttons)
2. Click "Add Student" → validates input → creates Student object → saves to MySQL Database
3. Click "View Students" → opens Frame2
4. Frame2 loads all students from MySQL Database into JList
5. User can filter students using:
   - Name filter (substring search)
   - Score range filter (min-max range)
   - Course filter (exact match)
   - Gender filter (exact match)
6. User can delete selected students or view class statistics.

## Features

- Simple two-frame GUI using Java Swing
- **MySQL Database Integration**: Persistent storage for student records
- **Improved UI Layout**: Frame1 uses GridBagLayout for clean, organized form layout
- Add students with name, score, course (dropdown), age (spinner), and gender
- **JSpinner for Age**: Integer-only input with up/down arrows
- View all students in a list
- Multiple filter options:
  - Filter by name
  - Filter by score range
  - Filter by course
  - Filter by gender
- **Delete Functionality**: Remove students from the database
- **Statistics View**: Check class average score, age, and gender distribution
- Basic input validation
- Beginner-friendly code structure

