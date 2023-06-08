/**
 * The AccessControlProxy class acts as a proxy for accessing controlled objects.
 * It provides access control checks and additional functionality before allowing method execution.
 * It implements the Proxy design pattern.
 */
public class AccessControlProxy<T extends ControlledObject> {
    private final T target;
    private final LoggingSingleton logger;

    private AccessControlProxy(T target) {
        this.target = target;
        this.logger = LoggingSingleton.getInstance();
    }

    /**
     * Factory method for creating an instance of AccessControlProxy.
     * @param target The controlled object to be proxied.
     * @param <T> The type of the controlled object.
     * @return The AccessControlProxy instance.
     */
    public static <T extends ControlledObject> AccessControlProxy<T> getInstance(T target) {
        return new AccessControlProxy<>(target);
    }

    /**
     * Returns the controlled object.
     * @return The controlled object.
     */
    private T getcontrolledobject() {
        return target;
    }

    /**
     * Checks if access is allowed based on the required role.
     * @param requiredRole The required role for access.
     * @return true if access is allowed, false otherwise.
     */
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

    /**
     * Enrolls a student in a course.
     * @param course The course to enroll in.
     */
    public void enroll(AccessControlProxy<Course> course) {
        if (isAccessAllowed("Student")) {
            Student student = (Student) target;
            student.enroll(course.getcontrolledobject());
            logger.logInfo("Enrolling student: " + student.getName() + " in course: " + course.getcontrolledobject().getCourseName());
        } else {
            logger.logWarning("Access denied for enrollStudent operation.");
        }
    }

    /**
     * Sets the grades for a student in a course.
     * @param controlledStudent The controlled student object.
     * @param controlledCourse The controlled course object.
     * @param grade The grade to be set.
     */
    public void setGrades(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledCourse, int grade) {
        if (isAccessAllowed("Professor")) {
            Student student = controlledStudent.target;
            Course course = controlledCourse.target;
            Database db = new Database();
            db.saveGrade(student, course, grade);
            logger.logInfo("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());
        } else {
            logger.logWarning("Access denied for setGrades operation.");
        }
    }

    /**
     * Sets the PVL for a student in a lab.
     * @param controlledStudent The controlled student object.
     * @param controlledCourse The controlled course object.
     * @param grade The grade to be set.
     */
    public void setPVL(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Lab> controlledLab, Boolean pvl) {
        if (isAccessAllowed("Professor")) {
            Student student = controlledStudent.target;
            Lab lab = controlledLab.target;
            Database db = new Database();
            db.setPVL(student, lab, pvl);
            logger.logInfo("Setting PVL: " + pvl + " for student: " + student.getName() + " in course: " + lab.getCourseName());
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
    public int saveLab(AccessControlProxy<Lab> controlledLab) {
        return ((Database) target).saveLab((Lab) controlledLab.target);
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

    Course createCourse( String courseName, int credits, AccessControlProxy<Professor> professor) {
        return SystemFactory.createCourse(courseName, credits, professor.getcontrolledobject());
    }
    Lab createLab(String courseName, int credits, AccessControlProxy<Professor> professor, AccessControlProxy<Course> course) {
        if (course != null && course.getcontrolledobject() != null) {
            return SystemFactory.createLab(courseName, credits, professor.getcontrolledobject(), course.getcontrolledobject());
        } else {
            return null;        
        }
    }

    public void enrolllab(AccessControlProxy<Lab> controlledLab) {        
        if (isAccessAllowed("Student")) {
        Student student = (Student) target;
        student.enrolllab(controlledLab.getcontrolledobject());
        logger.logInfo("Enrolling student: " + student.getName() + " in course: " + controlledLab.getcontrolledobject().getLabName());
    } else {
        logger.logWarning("Access denied for enrollStudent operation.");
    }
    }

    public void displayCourseInfolab(AccessControlProxy<Lab> controlledLab) {
        controlledLab.target.displayCourseInfo();
    }
    

}
