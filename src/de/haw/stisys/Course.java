package de.haw.stisys;
/**
 * The Course class represents a course that implements the ControlledObject interface.
 */
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

    /**
     * Saves the grade of a student in the course.
     * @param student The student.
     * @param pvl The PVL (Pass/Fail) status.
     * @param result The grade result.
     */
    public void saveGrade(Student student, int pvl, int result) {
        Database database = new Database();
        database.saveGrade(student, this, result);
        database.close();
    }

    /**
     * Displays information about the course.
     */
    public void displayCourseInfo() {
        Database database = new Database();
        database.displayCourseInfo(this.courseID);
        database.close();
    }

    /**
     * Gets the name of the course.
     * @return The course name.
     */
    public String getCourseName() {
        return this.courseName;
    }
    
    /**
     * Gets the ID of the course.
     * @return The course ID.
     */
    public int getCourseID() {
        return this.courseID;
    }

    /**
     * Sets the grades of a student in the course.
     * @param student The student.
     * @param course The course.
     * @param grade The grade to be set.
     */
    public void setGrades(Student student, Course course, int grade) {

    }

    /**
     * Gets the number of credits for the course.
     * @return The credits.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Sets the ID of the course.
     * @param id The course ID.
     */
    public void setId(int id) {
        this.courseID = id;
    }

    /**
     * Gets the name of the professor teaching the course.
     * @return The professor's name.
     */
    public String getProfessor() {
        return this.professor.getName();
    }

    /**
     * Sets the lab for the course.
     * @param lab The lab to be set.
     */
    public void setLab(Lab lab) {
        Database database = new Database();
        database.addLabToCourse(this.courseID, lab.getLabID());
        database.close();
    }



}
