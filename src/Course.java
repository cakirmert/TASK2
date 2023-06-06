// Course class implementing ControlledObject interface
class Course implements ControlledObject {
    private final String courseName;
    private final int credits;
    private final Professor professor;
    private int courseID;

    public Course(String courseName, int credits, Professor professor) {
        this.courseName = courseName;
        this.credits = credits;
        this.professor = professor;
    }

    public void enrollStudent(Student student) {
        Database database = new Database();
        database.enrollStudent(student, this.courseID);
        database.close();
    }

    public void saveGrade(Student student, int pvl, int result) {
        Database database = new Database();
        database.saveGrade(student, this, result);
        database.close();
    }

    public void displayCourseInfo() {
        Database database = new Database();
        database.displayCourseInfo(this.courseID);
        database.close();
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setGrades(Student student, Course course, int grade) {

    }

    public int getCredits() {
        return this.credits;
    }
    public void setId(int id) {
        this.courseID = id;
    }

    public String getProfessor() {
        return this.professor.getName();
    }

}
