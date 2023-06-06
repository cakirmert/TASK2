public class StiSys {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Create database instance
        Database database = new Database();

        // Create professor and instructor instances
        Professor professor = SystemFactory.createProfessor("John Doe", "cleartext");
        Professor instructor = SystemFactory.createProfessor("Jane Smith","password");

        // Create lab course
        Course lab = SystemFactory.createCourse("lab","Software Engineering", 3, instructor);
        // Create generic course
        Course genericCourse = SystemFactory.createCourse("generic","Software Engineering", 4, professor);

        //Save courses to database
        genericCourse.setId(database.saveCourse(genericCourse));
        lab.setId(database.saveCourse(lab));

        // Create student instance
        Student student = SystemFactory.createStudent("Alice Johnson","cleartext");

        student.setId(database.saveStudent(student));

        // Enroll the student in the courses
        genericCourse.enrollStudent(student);
        lab.enrollStudent(student);

        // Display course information
        lab.displayCourseInfo();
        genericCourse.displayCourseInfo();

        // Professor gives PVL to the student in the lab course
        instructor.setPVL(student, lab, true);
        // Student views PVL Status
        student.viewGrades();

        // Professor sets the grades for the student in the course
        professor.setGrades(student, genericCourse, 15);

        // Student views grades after exam
        student.viewGrades();


        System.out.println("Controlled operations: \n");
        //generate courses with AccessControlProxy

        AccessControlProxy<Professor> controlledinstructor = AccessControlProxy.getInstance(new Professor("Jane Doe", "cleartext"));
        AccessControlProxy<Database>  controlleddatabase = AccessControlProxy.getInstance(new Database());
        AccessControlProxy<Course> controlledgenericCourse = AccessControlProxy.getInstance(controlleddatabase.callfactory("lab","Software Engineering", 3, controlledinstructor));
        int controlledgenericCourseId = controlleddatabase.saveCourse(controlledgenericCourse, controlleddatabase);
        controlledgenericCourse.setCourseId(controlledgenericCourseId);
        controlledgenericCourse.displayCourseInfo(controlledgenericCourse);
        AccessControlProxy<Professor> controlledprofessor = AccessControlProxy.getInstance(new Professor("John Doe", "cleartext"));
        AccessControlProxy<Student> controlledStudent = AccessControlProxy.getInstance(new Student("Alice Johnson","cleartext"));
        int controlledid = controlleddatabase.saveStudent(controlledStudent, controlleddatabase);
        controlledStudent.setStudentId(controlledid);
        controlledgenericCourse.enrollStudent(controlledStudent, controlledgenericCourse);
        controlledprofessor.setGrades(controlledStudent, controlledgenericCourse, 15);
        controlledStudent.viewGrades(controlledStudent);

        // Close the database connection
        database.close();
        controlleddatabase.close(controlleddatabase);
    }
}
