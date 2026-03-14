<div align="center">

# 🧠 Java Exam Management System

A Java OOP project showcasing inheritance, polymorphism, abstract classes, interfaces, custom exception handling, file serialization, and robust business logic validation.

Course project for **Object-Oriented Programming** at Afeka College of Engineering.

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JUnit 5](https://img.shields.io/badge/JUnit_5-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[🇮🇱 לקריאה בעברית](README_HE.md)

</div>

---

## 📌 About

I built this project as a core assignment in my Object-Oriented Programming course. It’s a complete system designed to manage examinations in an academic environment—handling everything from creating questions to generating full exams with automated scoring logic.

What makes this project special is how it implements the fundamental building blocks of software engineering. It moves beyond simple exercises into a functional application where **Inheritance**, **Interfaces**, and **File Serialization** work together to create a persistent, scalable tool. I paid extra attention to error handling to ensure the system remains stable even with invalid user input.

---

## 📋 Assignment Features

*   **Question Bank Management**: Support for both Multiple-Choice and Open Questions.
*   **Dual Generation Modes**:
    *   **Manual**: Select specific questions and craft custom answers.
    *   **Automatic**: Instant exam generation based on random selection from the stock.
*   **Data Persistence**: Stock of questions is saved and loaded using Java Object Serialization (`.dat` files).
*   **Export to File**: Generates formatted `.txt` files for both the Exam and the Answer Key, complete with timestamps.

---

## 🧩 Advanced Concepts Demonstrated

*   **Object-Oriented Programming**: Clear class hierarchy (`Exam` and `Question` hierarchies).
*   **Interfaces & Abstraction**: Implementation of the `Examable` interface and `abstract` base classes to enforce contract-based design.
*   **Exception Handling**: Custom exception hierarchy (e.g., `NumOfQuestionsException`) for robust validation.
*   **Input Validation**: Strict checks for exam size, minimum answers, and correct answer counts.
*   **JUnit Testing**: Included unit tests to demonstrate commitment to code quality and reliability.

---

## 🏗️ Class Hierarchy

*   `Exam` (Abstract)
    *   `ManualExam`
    *   `AutomaticExam`
*   `Question` (Abstract)
    *   `MultiQuestion` (Multiple choice)
    *   `OpenQuestion`
*   `Answer` & `AnswerKey` logic
*   `ExamException` (Custom Exceptions)

---

## 🚀 How to Build & Run

### Prerequisites
*   Java JDK 11 or higher.

### Steps
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/GolanLevi/Java-Exam-Management-System.git
    ```
2.  **Compile the source**:
    ```bash
    javac *.java
    ```
3.  **Run the application**:
    ```bash
    java Program
    ```

---

## 🎯 What the Program Does

Once running, the program provides an interactive menu to:
1. Create a stock of questions or load an existing one.
2. Generate exams (Manual or Automatic).
3. Export exams and answers to external text files.
4. Add/Remove/Edit questions and answers in real-time.

---

## 👥 Author
*   **Golan Levi** - *Computer Science Graduate, Afeka College of Engineering*

---

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
