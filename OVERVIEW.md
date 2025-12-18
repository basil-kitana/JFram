# Application Overview

## Application Structure

```
Frame1 (Main Frame)
├── Name TextField
├── Score TextField (integer)
├── Grade ComboBox (dropdown: Freshman, Sophomore, Junior, Senior)
├── Age TextField (integer)
├── Add Student Button
│   └── Creates Student object with all fields
│       └── Adds to StudentData.students
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
├── Filter by Grade
│   ├── Grade ComboBox (dropdown with All/Freshman/Sophomore/Junior/Senior)
│   └── Filter Button
└── Reset All Filters Button
    └── Shows all students again
```

## Data Flow

1. User enters student data in Frame1:
   - Name (text)
   - Score (integer)
   - Grade (selected from dropdown)
   - Age (integer)
2. Click "Add Student" → validates input → creates Student object → stores in StudentData
3. Click "View Students" → opens Frame2
4. Frame2 loads all students from StudentData into JList
5. User can filter students using:
   - Name filter (substring search)
   - Score range filter (min-max range)
   - Grade filter (exact match)

## Features

- Simple two-frame GUI using Java Swing
- Add students with name, score, grade (dropdown), and age
- View all students in a list
- Multiple filter options:
  - Filter by name (text search)
  - Filter by score range (integer range)
  - Filter by grade level (dropdown selection)
- Basic input validation
- Beginner-friendly code structure

