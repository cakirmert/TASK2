class Lab extends Course{
    private int labID;
    private String labName;
    private Professor instructor;
    private int credits;
    private boolean labPVL;

    public Lab(String labName, int credits, Professor instructor) {
        super(labName, credits, instructor);
        this.labName = labName;
        this.instructor = instructor;
        this.credits = credits;
    }

    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }

    public String getLabName() {
        return labName;
    }

    public Professor getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    public boolean hasLabPVL() {
        return labPVL;
    }
    public void setId(int id) {
        this.labID = id;
    }
    public void setLabPVL(boolean pvl) {
        this.labPVL = pvl;
    }
}
