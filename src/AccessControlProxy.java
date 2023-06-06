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

    public void enrollStudent(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledgenericCourse) {
        Student student = controlledStudent.target;
        Course course = controlledgenericCourse.target;
        course.enrollStudent(student);
        logger.logInfo("Enrolling student: " + student.getName() + " in course: " + course.getCourseName());
    }

    public void setGrades(AccessControlProxy<Student> controlledstudent, AccessControlProxy<Course> controlledcourse, int grade) {
        Student student = controlledstudent.target;
        Course course = controlledcourse.target;
        Database db = new Database();
        db.saveGrade(student, course, grade);
        logger.logInfo("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());
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

    public void setPVL(AccessControlProxy<Student> controlledstudent, AccessControlProxy<Course> controlledcourse, int PVL) {
        Student student = controlledstudent.target;
        Course course = controlledcourse.target;
        Database db = new Database();
        db.setPVL(student, course, PVL);
        logger.logInfo("Setting PVL: " + PVL + " for student: " + student.getName() + " in course: " + course.getCourseName());
    }
}
