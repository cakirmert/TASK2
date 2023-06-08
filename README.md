# StiSys Application
Author: Mert Cakir
Version: 08.06.2023

## Overview
The StiSys application is a system for managing student enrollment and grades in courses and labs. It is implemented in Java and utilizes various design patterns to achieve modularity, flexibility, and maintainability.

## Design Patterns
The codebase implements the following design patterns:

Singleton Design Pattern:

The LoggingSingleton class represents a singleton logger instance. It ensures that only one instance of the logger is created throughout the application.
The singleton pattern guarantees global access to the logger object and provides a centralized logging mechanism.
This pattern is employed to encapsulate the logger functionality, promote code reusability, and ensure thread-safe access to the logger instance.
Proxy Design Pattern:

The AccessControlProxy class acts as a proxy for accessing controlled objects such as students, courses, and labs.
It provides access control checks and additional functionality before allowing method execution.
The proxy pattern enables fine-grained control over access to controlled objects, allowing for additional validation or logging operations.
In this codebase, the proxy is used to enforce access control based on user roles (Professor or Student) before performing operations like enrolling in courses, setting grades, and setting PVL (Pass/Fail) status.
Factory Method Design Pattern:

The SystemFactory class provides factory methods for creating various objects in the system, such as courses, labs, students, professors, and the database.
It encapsulates the object creation process and abstracts it from the client code.
The factory methods hide the complexity of object creation and allow the system to create objects dynamically based on specific parameters.
By utilizing the factory method pattern, the SystemFactory class centralizes object creation logic and provides a consistent interface for creating different types of objects.
This promotes loose coupling between the client code and the created objects, making the system more flexible and easily maintainable.
## Usage
To use the StiSys application, follow these steps:

Set up the MySQL database:

Make sure you have MySQL installed and running on your local machine.
Create a database named "stisys".
Set the database connection details (URL, username, password) in the Database class constructor.
Compile and run the StiSys application:

Compile all the Java files in the codebase.
Run the StiSys class, which contains the main method, to start the application.
Interact with the application:

Use the provided classes and methods to perform operations such as enrolling students, setting grades, displaying course information, and viewing grades.
## Conclusion
The StiSys application demonstrates the implementation of the Singleton, Proxy, and Factory Method design patterns. These patterns enhance the modularity, flexibility, and maintainability of the codebase by encapsulating functionality, providing access control, and abstracting object creation.

Author: Mert Cakir
Version: 08.06.2023