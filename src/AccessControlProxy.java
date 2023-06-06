/**
 * AccessControlProxy is the proxy class for the ControlledObject interface
 * and is the proxy for the Course and Student classes. It is responsible for
 * logging all the actions of the user and delegating method calls to the
 * ControlledObject interface.
 */
public
class AccessControlProxy<T extends ControlledObject> {
    private final T target;
    private final LoggingSingleton logger;

    private AccessControlProxy(T target) {
        this.target = target;
        this.logger = LoggingSingleton.getInstance();
    }

    public static <T extends ControlledObject> AccessControlProxy<T> getInstance(T target) {
        return new AccessControlProxy<>(target);
    }

    public T getControlledObject() {
        return target;
    }

    public void enrollStudent(AccessControlProxy<Student> controlledStudent, AccessControlProxy<Course> controlledgenericCourse) {
        Student student = controlledStudent.getControlledObject();
        Course course =controlledgenericCourse.getControlledObject();
        course.enrollStudent(student);
        logger.logInfo("Enrolling student: " + student.getName() + " in course: " + course.getCourseName());
    }

    public void setGrades(AccessControlProxy<Student> controlledstudent, AccessControlProxy<Course> controlledcourse, int grade) {
        Student student = controlledstudent.getControlledObject();
        Course course = controlledcourse.getControlledObject();
        Database db = new Database();
        db.saveGrade(student,course, 1, grade);
        logger.logInfo("Setting grade: " + grade + " for student: " + student.getName() + " in course: " + course.getCourseName());

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
      return  SystemFactory.createCourse(courseType,courseName,credits,prof);
    }
}