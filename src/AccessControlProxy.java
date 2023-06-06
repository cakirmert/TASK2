import java.io.IOException;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class AccessControlProxy<T extends ControlledObject> {
    private final T target;
    private final Logger logger;


    private AccessControlProxy(T target) {
        this.target = target;
        this.logger = Logger.getLogger(AccessControlProxy.class.getName());
        configureLogger();
    }


    public static <T extends ControlledObject> AccessControlProxy<T> getInstance(T target) {
        return new AccessControlProxy<>(target);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/stisys", "root", "123456");
    }

    public T getControlledObject() {
        return target;
    }

    public void enrollStudent(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledgenericCourse) {
        Student student = controlledStudent.getControlledObject();
        Course course =controlledgenericCourse.getControlledObject();
        course.enrollStudent(student);
        logger.info("Enrolling student: " + student.getName() + " in course: " + course.getCourseName());
    }

    private boolean authenticateUser(User user) {
        int id = user.getId();
        String password = user.getPassword();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    // If the query returns a result, authentication is successful
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean authorizeEnrollment(User student) {
        // Logic to authorize enrollment for the student in the course
        // Return true if the student is authorized, for now this is not fully implemented
        return authenticateUser(student);
    }

    //private boolean authorizeAccess(User professor) {
    // Logic to authorize access to the course information
    // Return true if the student is authorized, for now this is not fully implemented
    //   return authenticateUser(professor);
    //}
    private void configureLogger() {
        try {
            // Create a FileHandler to log messages into a file
            FileHandler fileHandler = new FileHandler("access.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Set the logging level and add the FileHandler to the logger
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGrades(AccessControlProxy<Student> controlledstudent, AccessControlProxy<Course> controlledcourse, int grade) {
        Student student = controlledstudent.getControlledObject();
        Course course = controlledcourse.getControlledObject();
        Database db = new Database();
        db.saveGrade(student,course, 1, grade);
        logger.info("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());

    }


    public void setCourseId(int course) {
        target.setId(course);
    }

    public void setStudentId(int id) {
        target.setId(id);
    }

    public void displayCourseInfo(AccessControlProxy<T> courseinfo) {
        courseinfo.getControlledObject().displayCourseInfo();
    }

    public int saveCourse(AccessControlProxy<Course> controlledgenericCourse, AccessControlProxy<Database> controlleddatabase) {
        Course course = controlledgenericCourse.getControlledObject();
        Database database = controlleddatabase.getControlledObject();
        return database.saveCourse(course);
    }
    public int saveStudent(AccessControlProxy<Student> controlledgenericCourse, AccessControlProxy<Database> controlleddatabase) {
        Student student = controlledgenericCourse.getControlledObject();
        Database database = controlleddatabase.getControlledObject();
        return database.saveStudent(student);
    }

    public void viewGrades(AccessControlProxy<Student> controlledStudent) {
        Student student = controlledStudent.getControlledObject();
        student.viewGrades();
    }

    public void close(AccessControlProxy<Database> controlleddatabase) {
        Database database = controlleddatabase.getControlledObject();
        database.close();
    }
    Course callfactory(String courseType, String courseName, int credits, AccessControlProxy<Professor> professor){
        Professor prof=professor.getControlledObject();
      return  CourseFactory.createCourse(courseType,courseName,credits,prof);
    }
}