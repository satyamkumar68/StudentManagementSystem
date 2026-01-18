# Project Structure and Class Diagram

## 📁 Complete File Structure

```
StudentManagementSystem/
│
├── 📄 README.md                          # Complete setup guide
├── 📄 QUICKSTART.md                      # 5-minute setup guide
├── 📄 VIVA_QUESTIONS.md                  # Interview Q&A (35 questions)
├── 📄 PROJECT_STRUCTURE.md               # This file
│
├── 📁 database/
│   └── 📄 setup.sql                      # Database creation script
│
└── 📁 src/
    └── 📁 com/
        └── 📁 sms/
            │
            ├── 📄 Main.java              # Entry point (50 lines)
            │
            ├── 📁 model/
            │   └── 📄 Student.java       # POJO class (100 lines)
            │
            ├── 📁 dao/
            │   └── 📄 StudentDAO.java    # CRUD operations (280 lines)
            │
            ├── 📁 util/
            │   └── 📄 DatabaseConnection.java  # Connection manager (70 lines)
            │
            └── 📁 ui/
                └── 📄 StudentFrame.java  # Swing GUI (650 lines)
```

**Total Lines of Code:** ~1,150 lines  
**Total Files:** 10 files  
**Package Structure:** 4 packages

---

## 🏗️ Class Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         Main.java                           │
│  ─────────────────────────────────────────────────────────  │
│  + main(String[] args): void                                │
│  - Tests database connection                                │
│  - Launches StudentFrame on EDT                             │
└────────────────────┬────────────────────────────────────────┘
                     │ creates
                     ↓
┌─────────────────────────────────────────────────────────────┐
│                    StudentFrame.java                        │
│                      (Swing JFrame)                         │
│  ─────────────────────────────────────────────────────────  │
│  Fields:                                                    │
│  - studentDAO: StudentDAO                                   │
│  - txtRollNumber, txtName, txtEmail, etc.: JTextField       │
│  - studentTable: JTable                                     │
│  - tableModel: DefaultTableModel                            │
│  - btnAdd, btnUpdate, btnDelete, etc.: JButton              │
│  ─────────────────────────────────────────────────────────  │
│  Methods:                                                   │
│  + StudentFrame()                                           │
│  - initializeUI(): void                                     │
│  - createFormPanel(): JPanel                                │
│  - createTablePanel(): JPanel                               │
│  - addStudent(): void                                       │
│  - updateStudent(): void                                    │
│  - deleteStudent(): void                                    │
│  - searchStudent(): void                                    │
│  - loadStudentData(): void                                  │
│  - validateInput(): boolean                                 │
│  - clearFields(): void                                      │
└────────────────────┬────────────────────────────────────────┘
                     │ uses
                     ↓
┌─────────────────────────────────────────────────────────────┐
│                    StudentDAO.java                          │
│                  (Data Access Object)                       │
│  ─────────────────────────────────────────────────────────  │
│  Constants:                                                 │
│  - INSERT_STUDENT: String                                   │
│  - SELECT_ALL_STUDENTS: String                              │
│  - UPDATE_STUDENT: String                                   │
│  - DELETE_STUDENT: String                                   │
│  - SELECT_STUDENT_BY_ROLL: String                           │
│  - SELECT_STUDENT_BY_NAME: String                           │
│  ─────────────────────────────────────────────────────────  │
│  Methods:                                                   │
│  + addStudent(Student): boolean                             │
│  + getAllStudents(): List<Student>                          │
│  + getStudentByRollNumber(String): Student                  │
│  + searchStudentsByName(String): List<Student>              │
│  + updateStudent(Student): boolean                          │
│  + deleteStudent(String): boolean                           │
│  + isRollNumberExists(String): boolean                      │
│  - extractStudentFromResultSet(ResultSet): Student          │
└────────────────────┬───────────────┬────────────────────────┘
                     │ uses          │ uses
                     ↓               ↓
┌──────────────────────────────┐  ┌─────────────────────────┐
│  DatabaseConnection.java     │  │    Student.java         │
│      (Singleton)             │  │      (POJO)             │
│  ──────────────────────────  │  │  ─────────────────────  │
│  Fields:                     │  │  Fields:                │
│  - URL: String (static)      │  │  - rollNumber: String   │
│  - USERNAME: String (static) │  │  - name: String         │
│  - PASSWORD: String (static) │  │  - email: String        │
│  - DRIVER: String (static)   │  │  - phone: String        │
│  - connection: Connection    │  │  - course: String       │
│  ──────────────────────────  │  │  - marks: double        │
│  Methods:                    │  │  - createdAt: Timestamp │
│  + getConnection(): Connection│ │  - updatedAt: Timestamp │
│  + closeConnection(): void   │  │  ─────────────────────  │
│  + testConnection(): boolean │  │  Methods:               │
│  - DatabaseConnection()      │  │  + Student()            │
│                              │  │  + Student(params...)   │
│                              │  │  + getters/setters      │
│                              │  │  + toString(): String   │
└──────────────────────────────┘  └─────────────────────────┘
```

---

## 🔄 Data Flow Diagram

```
┌──────────┐         ┌─────────────┐         ┌──────────┐         ┌──────────┐
│   USER   │ ──────> │ StudentFrame│ ──────> │StudentDAO│ ──────> │  MySQL   │
│          │         │   (Swing)   │         │  (JDBC)  │         │ Database │
└──────────┘         └─────────────┘         └──────────┘         └──────────┘
     ↑                      │                      │                     │
     │                      │                      │                     │
     │                      ↓                      ↓                     │
     │               ┌─────────────┐        ┌──────────┐                │
     │               │  Validation │        │ Database │                │
     │               │   & Events  │        │Connection│                │
     │               └─────────────┘        └──────────┘                │
     │                                                                   │
     └───────────────────────────────────────────────────────────────────┘
                           Response / Data Display
```

### Flow Steps:
1. **User Action** → Button click (Add/Update/Delete/Search)
2. **UI Layer** → Validates input, creates Student object
3. **DAO Layer** → Executes SQL via PreparedStatement
4. **Database** → Processes query, returns result
5. **DAO Layer** → Converts ResultSet to Student objects
6. **UI Layer** → Updates table, shows message
7. **User** → Sees updated data

---

## 🎯 Method Call Flow Examples

### Add Student Flow
```
User clicks "Add Student"
    ↓
StudentFrame.addStudent()
    ↓
validateInput() → returns true/false
    ↓
new Student(...) → creates object
    ↓
studentDAO.addStudent(student)
    ↓
isRollNumberExists() → checks duplicate
    ↓
DatabaseConnection.getConnection()
    ↓
PreparedStatement.executeUpdate()
    ↓
MySQL INSERT query
    ↓
Return success/failure
    ↓
JOptionPane.showMessageDialog()
    ↓
loadStudentData() → refresh table
    ↓
clearFields()
```

### Search Student Flow
```
User enters search text and clicks "Search"
    ↓
StudentFrame.searchStudent()
    ↓
studentDAO.getStudentByRollNumber(searchText)
    ↓
If found → display in table
    ↓
If not found → studentDAO.searchStudentsByName(searchText)
    ↓
DatabaseConnection.getConnection()
    ↓
PreparedStatement with LIKE query
    ↓
ResultSet → List<Student>
    ↓
Display results in table
```

---

## 📊 Database Schema

```sql
┌─────────────────────────────────────────────────┐
│              students table                     │
├──────────────┬──────────────┬──────────────────┤
│ Column       │ Type         │ Constraints      │
├──────────────┼──────────────┼──────────────────┤
│ roll_number  │ VARCHAR(20)  │ PRIMARY KEY      │
│ name         │ VARCHAR(100) │ NOT NULL         │
│ email        │ VARCHAR(100) │ NOT NULL, UNIQUE │
│ phone        │ VARCHAR(15)  │ NOT NULL         │
│ course       │ VARCHAR(50)  │ NOT NULL         │
│ marks        │ DECIMAL(5,2) │ CHECK (0-100)    │
│ created_at   │ TIMESTAMP    │ DEFAULT NOW()    │
│ updated_at   │ TIMESTAMP    │ ON UPDATE NOW()  │
└──────────────┴──────────────┴──────────────────┘
```

**Indexes:**
- PRIMARY KEY on `roll_number` (automatic)
- UNIQUE constraint on `email`

**Relationships:**
- Currently standalone table
- Future: Can add foreign keys for courses, departments

---

## 🔐 Security Features

| Feature | Implementation | Location |
|---------|---------------|----------|
| SQL Injection Prevention | PreparedStatement | StudentDAO.java |
| Input Validation | Regex patterns | StudentFrame.validateInput() |
| Duplicate Prevention | Database check | StudentDAO.isRollNumberExists() |
| Email Validation | Regex | StudentFrame.validateInput() |
| Marks Range Check | 0-100 validation | Database + UI |
| Connection Security | Try-with-resources | All DAO methods |

---

## 📦 Dependencies

### Required Libraries
```xml
<!-- Maven format (for reference) -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Built-in Libraries (No external dependency needed)
- `java.sql.*` - JDBC
- `javax.swing.*` - GUI
- `java.awt.*` - Layout managers
- `java.util.*` - Collections

---

## 🎨 UI Component Hierarchy

```
JFrame (StudentFrame)
│
├── BorderLayout
│   │
│   ├── NORTH: Top Panel (Title)
│   │   └── JLabel ("STUDENT MANAGEMENT SYSTEM")
│   │
│   ├── CENTER: Main Panel
│   │   ├── WEST: Form Panel
│   │   │   ├── GridBagLayout
│   │   │   ├── JLabels (6)
│   │   │   ├── JTextFields (6)
│   │   │   └── Button Panel
│   │   │       ├── Add Button
│   │   │       ├── Update Button
│   │   │       ├── Delete Button
│   │   │       └── Clear Button
│   │   │
│   │   └── CENTER: Table Panel
│   │       └── JScrollPane
│   │           └── JTable (6 columns)
│   │
│   └── SOUTH: Bottom Panel
│       ├── Search Label
│       ├── Search TextField
│       ├── Search Button
│       └── Refresh Button
```

---

## 🧪 Testing Scenarios

### Unit Testing Targets
```
StudentDAO:
├── testAddStudent()
├── testAddDuplicateStudent()
├── testGetAllStudents()
├── testGetStudentByRollNumber()
├── testSearchStudentsByName()
├── testUpdateStudent()
├── testDeleteStudent()
└── testIsRollNumberExists()

DatabaseConnection:
├── testGetConnection()
├── testCloseConnection()
└── testTestConnection()

Student:
├── testConstructors()
├── testGettersSetters()
└── testToString()
```

---

## 📈 Code Metrics

| Metric | Value |
|--------|-------|
| Total Classes | 5 |
| Total Methods | ~45 |
| Lines of Code | ~1,150 |
| Packages | 4 |
| SQL Queries | 7 |
| UI Components | 20+ |
| Validation Rules | 5 |
| Design Patterns | 3 |

---

## 🚀 Execution Flow

```
1. JVM starts
   ↓
2. Main.main() executes
   ↓
3. DatabaseConnection.testConnection()
   ↓
4. If success → SwingUtilities.invokeLater()
   ↓
5. StudentFrame constructor
   ↓
6. initializeUI()
   ↓
7. loadStudentData()
   ↓
8. Application ready
   ↓
9. User interactions (event-driven)
   ↓
10. CRUD operations via DAO
    ↓
11. Database updates
    ↓
12. UI refresh
```

---

## 💾 Memory Management

**Resource Cleanup:**
- Try-with-resources for Connection, Statement, ResultSet
- Automatic garbage collection for Student objects
- Singleton pattern prevents multiple connections
- Table model cleared before refresh

**Best Practices:**
- Close database resources immediately after use
- Don't hold references to large ResultSets
- Use PreparedStatement pooling (future enhancement)

---

**Document Version:** 1.0  
**Last Updated:** January 2026  
**Maintained By:** Development Team
