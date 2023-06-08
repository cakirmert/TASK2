package de.haw.stisys;
/**
 * The Professor class represents a professor that extends the User class.
 */
class Professor extends User {
    private final String password;
    private int id;
    public Professor(String name, String password) {
        super(name,password);
        this.password = password;
    }

    /**
     * Sets the grades for a student in a course.
     * @param student The student.
     * @param course The course.
     * @param grade The grade to be set.
     */
    public void setGrades(Student student, Course course, int grade) {
        Database db = new Database();
        db.saveGrade(student, course, grade);
        db.close();
    }

    // Implemented any additional methods from User class and therefore ControlledObject interface.

    public String getCourseName() {
        return null;
    }
    public int getId() {
        return id;
    }
    public int getCredits() {
        return 0;
    }

    public void displayCourseInfo() {

    }

    public int getCourseID() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
}
