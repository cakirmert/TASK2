
// Lab class   
class Lab extends Course {
    
    public Lab(String courseName, int credits, Professor professor) {
        super(courseName, credits, professor);
    }
    public void setPVL(Student student, Course course, int pvl) {
        Database db = new Database();
        db.setPVL(student, course, pvl);
        db.close();
    }
}
