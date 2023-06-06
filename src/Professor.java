// Professor class (subclass of Employee)
class Professor extends User {
    private final String password;
    private int id;
    public Professor(String name, String password) {
        super(name,password);
        this.password = password;
    }

    public String getCourseName() {
        return null;
    }
    public int getId() {
        return id;
    }
    public int getCredits() {
        return 0;
    }

    public void enrollStudent(Student student) {

    }

    public void displayCourseInfo() {

    }

    public int getCourseID() {
        return 0;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setGrades(Student student, Course course, int grade) {
        Database db = new Database();
        int pvl=0;
        db.saveGrade(student, course, pvl, grade);
        db.close();
    }
    public void setPVL(Student student, Course course, boolean pvl) {
        Database db = new Database();
        db.setPVL(student, course, pvl);
        db.close();
    }
    public void saveGrade(Student student, int pvl, int result) {

    }

    public String getPassword() {
        return password;
    }
}
