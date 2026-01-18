# Quick Start Guide - Student Management System

## ⚡ 5-Minute Setup

### Step 1: Database Setup (2 minutes)
```bash
# Login to MySQL
mysql -u root -p

# Run these commands
CREATE DATABASE student_management_db;
USE student_management_db;

# Copy-paste the CREATE TABLE statement from setup.sql
```

### Step 2: Configure Connection (1 minute)
Open `DatabaseConnection.java` and update:
```java
private static final String PASSWORD = "your_mysql_password";
```

### Step 3: Add JDBC Driver (1 minute)
- Download: https://dev.mysql.com/downloads/connector/j/
- Add JAR to project classpath (see README.md for IDE-specific steps)

### Step 4: Run (1 minute)
- Open `Main.java`
- Click Run
- Done! 🎉

---

## 🎯 Quick Test

1. **Add Student:** Fill form → Click "Add Student"
2. **View:** See student in table
3. **Update:** Click row → Modify → Click "Update Student"
4. **Delete:** Click row → Click "Delete Student"
5. **Search:** Enter name/roll → Click "Search"

---

## ⚠️ Quick Troubleshooting

| Error | Solution |
|-------|----------|
| ClassNotFoundException | Add JDBC driver to classpath |
| Access denied | Check MySQL password |
| Unknown database | Run setup.sql |
| Connection failed | Start MySQL service |

---

## 📋 Project Files Checklist

- [x] `Main.java` - Entry point
- [x] `Student.java` - Model class
- [x] `StudentDAO.java` - Database operations
- [x] `DatabaseConnection.java` - Connection manager
- [x] `StudentFrame.java` - Swing UI
- [x] `setup.sql` - Database script
- [x] `README.md` - Full documentation
- [x] `VIVA_QUESTIONS.md` - Interview prep

---

## 🔗 Useful Links

- [MySQL Download](https://dev.mysql.com/downloads/mysql/)
- [JDBC Driver](https://dev.mysql.com/downloads/connector/j/)
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

---

**Need Help?** Check README.md for detailed instructions!
