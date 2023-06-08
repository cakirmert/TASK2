/**
 * The Lab class represents a lab course that extends the Course class.
 */
class Lab extends Course{
    private int labID;
    private String labName;
    private Professor instructor;
    private int credits;
    private boolean labPVL;
    private Course course;

    public Lab(String labName, int credits, Professor instructor, Course course) {
        super(labName, credits, instructor);
        this.labName = labName;
        this.credits = credits;
        this.instructor = instructor;
        this.course = course;
    }
    

    public Course getCourse() {
        return course;
    }

    public void displayCourseInfo() {
        Database database = new Database();
        database.displayCourseInfo(this.labID);
        database.close();
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * Gets the ID of the lab.
     * @return The lab ID.
     */
    public int getLabID() {
        return labID;
    }

    /**
     * Sets the ID of the lab.
     */
    public void setLabID(int labID) {
        this.labID = labID;
    }

    /**
     * Gets the name of the lab.
     * @return The lab name.
     */
    public String getLabName() {
        return labName;
    }

    /**
     * Gets the instructor of the lab.
     * @return The instructor.
     */
    public Professor getInstructor() {
        return instructor;
    }

    /**
     * Gets the credits of the lab.
     * @return The lab credits.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Checks if the lab has a PVL (Pass/Fail) status.
     * @return true if the lab has PVL, false otherwise.
     */
    public boolean hasLabPVL() {
        return labPVL;
    }

    /**
     * Sets the ID of the lab.
     * @param id The lab ID.
     */
    public void setId(int id) {
        this.labID = id;
    }
    
    /**
     * Sets the PVL (Pass/Fail) status of the lab.
     * @param pvl The PVL status to be set.
     */
    public void setLabPVL(boolean pvl) {
        this.labPVL = pvl;
    }
}
