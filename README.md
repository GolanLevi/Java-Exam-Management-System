# 🧠 Java Exam Management System

A robust, Object-Oriented Java application designed for academic environments to manage, create, and export examinations. This project demonstrates core software engineering principles and advanced Java features.

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/OOP-Principles-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/File%20I%2FO-Serialization-green?style=for-the-badge" />
</p>

---

## 🚀 Key Features

*   **Dual Exam Modes**: 
    *   **Manual**: Hand-pick specific questions from the stock and define their answers.
    *   **Automatic**: System randomly selects questions and answers to generate a balanced exam instantly.
*   **Question Types**: Supports both **Multiple Choice** (with automatic verification logic) and **Open Questions**.
*   **Data Persistence**: Full state saving and loading using **Java Serialization**, ensuring your question stock is preserved between sessions.
*   **Export Options**: Generate professional formatted exams and answer keys directly to `.txt` files with timestamps.
*   **Robust Error Handling**: Custom exception hierarchy to validate business logic (e.g., minimum answers per question, exam size limits).

---

## 🏗️ Architecture & Design Patterns

This project was built with a strong focus on clean code and **SOLID** principles:

*   **Inheritance & Polymorphism**: Leveraged to handle different `Question` and `Exam` types through a single unified interface.
*   **Abstraction**: Extensive use of `abstract` classes and `Interfaces` (`Examable`) to decouple logic.
*   **Template Method Pattern**: Used in the exam generation flow where subclasses define specific steps of the creation process.
*   **Custom Exceptions**: A dedicated package for domain-specific errors, improving debugging and user feedback.

---

## 🛠️ Technical Stack

*   **Language**: Java (JDK 8+)
*   **APIs**: Java I/O, Serializable, Collections API.
*   **Tools**: Designed for Eclipse/IntelliJ/VS Code environments.

---

## 💻 Getting Started

1.  Clone the repository.
2.  Compile the `.java` files: `javac *.java`
3.  Run the main program: `java Program`
4.  The system will automatically create a `StockOfQuestions.dat` file on its first run.

---

## 🤝 Connections
*Developed as part of the Object-Oriented Programming course at Afeka College of Engineering.*
