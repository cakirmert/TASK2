public class AccessControlProxy<T extends ControlledObject> {
    private final T target;
    private final LoggingSingleton logger;

    private AccessControlProxy(T target) {
        this.target = target;
        this.logger = LoggingSingleton.getInstance();
    }

    public static <T extends ControlledObject> AccessControlProxy<T> getInstance(T target) {
        return new AccessControlProxy<>(target);
    }

    private boolean isAccessAllowed(String requiredRole) {
        
        // Check the role of the current user against the required role
        // Return true if access is allowed, false otherwise
        
        if (requiredRole.equals("Professor")) {
            if (target instanceof Professor) {
                return true;
            }
        } else if (requiredRole.equals("Student")) {
            if (target instanceof Student) {
                return true;
            }
        }  
        
        return false;
    }
    public void enroll(AccessControlProxy<Course> course) {
        if (isAccessAllowed("student")) {
            Student student = (Student) target;
            student.enroll((Course) target);
            logger.logInfo("Enrolling student: " + student.getName() + " in course: " + target.getCourseName());
        } else {
            logger.logWarning("Access denied for enrollStudent operation.");
        }
    }

    public void setGrades(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledCourse, int grade) {
        if (isAccessAllowed("professor")) {
            Student student = controlledStudent.target;
            Course course = controlledCourse.target;
            Database db = new Database();
            db.saveGrade(student, course, grade);
            logger.logInfo("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());
        } else {
            logger.logWarning("Access denied for setGrades operation.");
        }
    }

    public void setPVL(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledCourse, Boolean pvl) {
        if (isAccessAllowed("professor")) {
            Student student = controlledStudent.target;
            Course course = controlledCourse.target;
            Database db = new Database();
            db.setPVL(student, course, pvl);
            logger.logInfo("Setting PVL: " + pvl + " for student: " + student.getName() + " in course: " + course.getCourseName());
        } else {
            logger.logWarning("Access denied for setPVL operation.");
        }
    }

    public void setCourseId(int course) {
        target.setId(course);
    }

    public void setStudentId(int id) {
        target.setId(id);
    }

    public void displayCourseInfo(AccessControlProxy<Course> controlledcourse) {
        controlledcourse.target.displayCourseInfo();
    }

    public int saveCourse(AccessControlProxy<Course> controlledCourse) {
        return ((Database) target).saveCourse(controlledCourse.target);
    }

    public int saveStudent(AccessControlProxy<Student> controlledstudent) {
        return ((Database) target).saveStudent(controlledstudent.target);
    }

    public void viewGrades() {
        ((Student) target).viewGrades();
    }

    public void close() {
        ((Database) target).close();
    }

    Course callFactory(String courseType, String courseName, int credits, AccessControlProxy<Professor> professor) {
        return SystemFactory.createCourse(courseType, courseName, credits, professor.target);
    }

}
