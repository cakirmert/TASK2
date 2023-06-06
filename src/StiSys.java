public class StiSys {
    public static void main(String[] args) {

// Create controlled database instance
AccessControlProxy<Database> controlledDatabase = AccessControlProxy.getInstance(SystemFactory.createDatabase());

// Create controlled professor and instructor instances
AccessControlProxy<Professor> controlledInstructor = AccessControlProxy.getInstance(SystemFactory.createProfessor("Jane Doe", "cleartext"));
AccessControlProxy<Professor> controlledProfessor = AccessControlProxy.getInstance(SystemFactory.createProfessor("John Doe", "cleartext"));

// Create controlled lab course
AccessControlProxy<Course> controlledCourse = AccessControlProxy.getInstance(controlledDatabase.callFactory("generic","Software Engineering", 3, controlledProfessor,null));
AccessControlProxy<Course> controlledLab = AccessControlProxy.getInstance(controlledDatabase.callFactory("lab","Software Engineering", 3, controlledInstructor,controlledCourse));

controlledLab.setCourseId(controlledDatabase.saveCourse(controlledLab));
controlledCourse.setCourseId(controlledDatabase.saveCourse(controlledCourse));

// Create controlled student instance
AccessControlProxy<Student> controlledStudent = AccessControlProxy.getInstance(SystemFactory.createStudent("Alice Johnson", "cleartext"));

int controlledStudentId = controlledDatabase.saveStudent(controlledStudent);
controlledStudent.setStudentId(controlledStudentId);

// Enroll the student in the courses
controlledStudent.enroll(controlledLab);
controlledStudent.enroll(controlledCourse);

// Display course information
controlledLab.displayCourseInfo(controlledLab);
controlledLab.displayCourseInfo(controlledCourse);

// Professor gives PVL and grade to the student in the lab course
controlledInstructor.setPVL(controlledStudent, controlledLab, true);
// Student views PVL Status
controlledStudent.viewGrades();

// Professor sets the grades for the student in the course
controlledProfessor.setGrades(controlledStudent, controlledCourse, 15);

// Student views grades after exam
controlledStudent.viewGrades();

// Close the controlled database connection
controlledDatabase.close();

}

}