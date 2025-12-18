# Application Overview

## Application Structure

```
Frame1 (Main Frame)
├── Name TextField
├── Score TextField  
├── Add Student Button
│   └── Creates Student object
│       └── Adds to StudentData.students
└── View Students Button
    └── Opens Frame2

Frame2 (Display Frame)
├── JList (shows all students)
├── Filter TextField
├── Filter Button
│   └── Filters students by name
└── Reset Button
    └── Shows all students again
```

## Data Flow

1. User enters student name and score in Frame1
2. Click "Add Student" → validates input → creates Student object → stores in StudentData
3. Click "View Students" → opens Frame2
4. Frame2 loads all students from StudentData into JList
5. User can filter students by name using the filter field

## Features

- Simple two-frame GUI using Java Swing
- Add students with name and score
- View all students in a list
- Filter students by name
- Basic input validation
- Beginner-friendly code structure
