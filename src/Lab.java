
// Lab class   
class Lab extends Course {
    private boolean PVL;
    
    public Lab(String courseName, int credits, Professor professor) {
        super(courseName, credits, professor);
    }
    @Override
    public void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("PVL Status: " + (PVL ? "Pass" : "Fail"));
    }
}
