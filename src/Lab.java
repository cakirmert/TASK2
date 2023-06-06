
// Lab class   
class Lab extends Course {
    private int courseID;
    private boolean isLab;
    private boolean PVL;
    
    public Lab(String courseName, int credits, Professor professor) {
        super(courseName, credits, professor);

        this.isLab = true;
    }
    @Override
    public void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("PVL Status: " + (PVL ? "Pass" : "Fail"));
    }



}
