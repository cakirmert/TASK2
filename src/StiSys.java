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
        Professor professor = new Professor("John Doe", "cleartext");
        Professor instructor = new Professor("Jane Smith","password");

        // Create lab course
        Course labCourse = CourseFactory.createCourse("lab","Software Engineering LAB", 3, instructor);
        // Create generic course
        Course genericCourse = CourseFactory.createCourse("generic","Software Engineering", 4, professor);

        //Save courses to database
        int genericCourseId = database.saveCourse(genericCourse);
        genericCourse.setId(genericCourseId);
        int labCourseId = database.saveCourse(labCourse);
        labCourse.setId(labCourseId);

        // Create student instance
        Student student = new Student("Alice Johnson","cleartext");

        int studentId = database.saveStudent(student);
        student.setId(studentId);

        // Enroll the student in the courses
        genericCourse.enrollStudent(student);
        labCourse.enrollStudent(student);

        // Display course information
        labCourse.displayCourseInfo();
        genericCourse.displayCourseInfo();

        // Professor gives PVL to the student in the lab course
        database.saveGrade(student, labCourse, 1, 0);
        // Student views PVL Status
        student.viewGrades();

        // Professor sets the grades for the student in the lab course
        professor.setGrades(student, labCourse, 15);

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
