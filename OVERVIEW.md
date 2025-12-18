# Application Overview

## Application Structure

```
Frame1 (Main Frame) - Using GridBagLayout
├── Name TextField
├── Score TextField (integer)
├── Course ComboBox (dropdown: Math, Calculus, Physics, Chemistry, Biology)
├── Age JSpinner (integer with up/down arrows)
├── Add Student Button
│   └── Creates Student object with all fields
│       └── Adds to StudentData.students
└── View Students Button
    └── Opens Frame2
├── Gender Radio Buttons (Male/Female)

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
└── Reset All Filters Button
    └── Shows all students again
```

## Data Flow

1. User enters student data in Frame1:
   - Name (text)
   - Score (integer)
   - Course (selected from dropdown)
   - Age (integer using spinner)
  - Gender (radio buttons)
2. Click "Add Student" → validates input → creates Student object → stores in StudentData
3. Click "View Students" → opens Frame2
4. Frame2 loads all students from StudentData into JList
5. User can filter students using:
   - Name filter (substring search)
   - Score range filter (min-max range)
   - Course filter (exact match)
  - Gender filter (exact match)

## Features

- Simple two-frame GUI using Java Swing
- **Improved UI Layout**: Frame1 uses GridBagLayout for clean, organized form layout
- Add students with name, score, course (dropdown), and age (spinner)
- Add students with gender (Male/Female)
- **JSpinner for Age**: Integer-only input with up/down arrows, no string input allowed
- View all students in a list
- Multiple filter options:
  - Filter by name (text search)
  - Filter by score range (integer range)
  - Filter by course (dropdown selection)
  - Filter by gender (dropdown selection)
- Basic input validation
- Beginner-friendly code structure

