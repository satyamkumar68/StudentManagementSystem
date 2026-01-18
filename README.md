# Student Management System

![Java](https://img.shields.io/badge/Java-22.0.1-orange?logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![Swing](https://img.shields.io/badge/GUI-Swing-green)
![JDBC](https://img.shields.io/badge/Database-JDBC-red)

A complete desktop application for managing student records built with Java, Swing, MySQL, and JDBC. Features a professional UI with full CRUD operations, search functionality, and input validation.

## ✨ Features

- ✅ **Complete CRUD Operations** - Create, Read, Update, Delete student records
- ✅ **Search Functionality** - Search by roll number or name (partial match)
- ✅ **Input Validation** - Email format, phone number, marks range (0-100)
- ✅ **Duplicate Prevention** - Prevents duplicate roll numbers
- ✅ **Professional UI** - Color-coded buttons, responsive layout
- ✅ **Error Handling** - User-friendly error messages
- ✅ **Design Patterns** - Singleton, DAO, MVC architecture

## 🖼️ Screenshots

### Main Application Window
*Add a screenshot of your running application here*

### Features Demo
*Add screenshots showing Add, Update, Delete, Search operations*

## 🎥 Demo Video

*Add link to demo video here (YouTube, Loom, etc.)*

## 🛠️ Technologies Used

| Technology | Purpose | Version |
|------------|---------|---------|
| Java | Core programming language | 22.0.1 |
| Swing | GUI framework | Built-in |
| MySQL | Database management | 8.0 |
| JDBC | Database connectivity | 8.x |

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java JDK** 8 or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **MySQL Server** 5.7 or higher ([Download](https://dev.mysql.com/downloads/mysql/))
- **MySQL JDBC Driver** ([Download](https://dev.mysql.com/downloads/connector/j/))
- **IDE** - IntelliJ IDEA, Eclipse, or NetBeans (optional)

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/student-management-system.git
cd student-management-system
```

### 2. Setup MySQL Database

```bash
# Login to MySQL
mysql -u root -p

# Run the setup script
source database/setup.sql
```

Or manually create the database using MySQL Workbench.

### 3. Configure Database Connection

Update `src/com/sms/util/DatabaseConnection.java` with your MySQL credentials:

```java
private static final String PASSWORD = "your_mysql_password";
```

### 4. Add MySQL JDBC Driver

- Download MySQL Connector/J from [here](https://dev.mysql.com/downloads/connector/j/)
- Add the JAR file to your project classpath

**IntelliJ IDEA:**
1. File → Project Structure → Libraries
2. Click "+" → Java
3. Select the downloaded JAR file

### 5. Run the Application

```bash
# Compile
javac -cp ".;mysql-connector-j-8.x.x.jar" -d bin src/com/sms/**/*.java

# Run
java -cp ".;bin;mysql-connector-j-8.x.x.jar" com.sms.Main
```

Or simply run `Main.java` from your IDE.

## 📖 Usage

### Adding a Student
1. Fill in all fields (Roll Number, Name, Email, Phone, Course, Marks)
2. Click "Add Student"
3. Student appears in the table

### Updating a Student
1. Click on a student row in the table
2. Modify the fields
3. Click "Update Student"

### Deleting a Student
1. Click on a student row
2. Click "Delete Student"
3. Confirm deletion

### Searching Students
1. Enter roll number or name in search box
2. Click "Search"
3. Results displayed in table

## 🏗️ Project Structure

```
src/
└── com/
    └── sms/
        ├── Main.java                    # Entry point
        ├── model/
        │   └── Student.java             # Student POJO
        ├── dao/
        │   └── StudentDAO.java          # Database operations
        ├── util/
        │   └── DatabaseConnection.java  # Connection manager
        └── ui/
            └── StudentFrame.java        # Swing GUI
```

## 🎯 Design Patterns Used

- **Singleton Pattern** - DatabaseConnection (single instance)
- **DAO Pattern** - StudentDAO (data access abstraction)
- **MVC Pattern** - Model (Student), View (StudentFrame), Controller (DAO)

## 🔒 Security Features

- **SQL Injection Prevention** - PreparedStatements
- **Input Validation** - Email, phone, marks validation
- **Duplicate Prevention** - Database constraints
- **Error Handling** - Try-catch blocks with user-friendly messages

## 📚 Documentation

- [README.md](README.md) - This file
- [QUICKSTART.md](QUICKSTART.md) - Quick setup guide
- [VIVA_QUESTIONS.md](VIVA_QUESTIONS.md) - Interview Q&A (35 questions)
- [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) - Architecture details
- [JDBC_DRIVER_GUIDE.md](JDBC_DRIVER_GUIDE.md) - JDBC setup help

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Your Name**
- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)
- LinkedIn: [Your Profile](https://linkedin.com/in/yourprofile)
- Email: your.email@example.com

## 🙏 Acknowledgments

- Built as a learning project to demonstrate Java desktop application development
- Uses MySQL for robust data management
- Implements industry-standard design patterns

## 📞 Support

For issues or questions:
1. Check the [documentation](README.md)
2. Review [VIVA_QUESTIONS.md](VIVA_QUESTIONS.md)
3. Open an [issue](https://github.com/YOUR_USERNAME/student-management-system/issues)

---

**⭐ If you find this project useful, please consider giving it a star!**
