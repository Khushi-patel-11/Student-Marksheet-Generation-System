# Student-Marksheet-Generation-System

This project is a **Student Marksheet Generation System** built in Java. It allows users to add, display, and manage student records, generate marksheets, and interact with a MySQL database for persistent storage.

## Features

- Add new student records with marks for different semesters.
- Generate and print detailed marksheets for students.
- Display all students.
- Delete student records.
- View top 10 students by SPI for each semester.
- Remedial support for failed students.
- MySQL database integration for storing student data and marksheets.

## Project Structure

```
src/
  DatabaseConnection.java
  DoublyLinkedList.java
  FileOperations.java
  LoginRegister.java
  Main.java
  Marks.java
  MarkSheetGenerationSystem.java
  Queue.java
  Student.java
23002170110114.txt
photo1.png
.idea/
Ganesha.iml
```

- **src/**: Contains all Java source files.
- **DatabaseConnection.java**: Handles all database operations.
- **MarkSheetGenerationSystem.java**: Main entry point for the application.
- **Student.java, Marks.java**: Student and marks data models.
- **FileOperations.java**: Handles file I/O for marksheet generation.
- **DoublyLinkedList.java, Queue.java**: Data structures for managing students and rankings.
- **LoginRegister.java**: (Commented) User authentication logic.
- **23002170110114.txt**: Example generated marksheet.

## Requirements

- Java 8 or above (project uses JDK 20 in `.idea/misc.xml`)
- MySQL database (update credentials in `DatabaseConnection.java`)
- MySQL JDBC Driver (`com.mysql.cj.jdbc.Driver`)

## Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/khushi-patel-11/marksheet-generation-system.git
   cd marksheet-generation-system
   ```

2. **Configure Database:**
   - Create a MySQL database named `new`.
   - Create tables for `Semester_I`, `Semester_II`, and any required stored procedures (see code for details).
   - Update `URL`, `USER`, and `PASSWORD` in [`DatabaseConnection.java`](src/DatabaseConnection.java) as needed.

3. **Build and Run:**
   - Compile all Java files in `src/`.
   - Run `MarkSheetGenerationSystem.java` as the main class.

   Example (from project root):
   ```sh
   javac -d out src/*.java
   java -cp out;path/to/mysql-connector-java.jar MarkSheetGenerationSystem
   ```

## Usage

- Follow the on-screen menu to add, display, delete students, or generate/view marksheets.
- Marksheet files are generated as `<enrollmentNo>.txt` in the project directory.

## Notes

- The project uses a simple CLI interface.
- Ensure MySQL server is running and accessible.
- The `LoginRegister.java` file contains commented code for user authentication, which can be enabled and extended as needed.

## License

This project is for educational purposes.

---

**Author:** Khushi Patel  
