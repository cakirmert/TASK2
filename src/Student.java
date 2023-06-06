// Student class
class Student extends User {


    private final String name;
    private final String password;
    private int id;

    public Student(String name, String password) {
        super(name,password);
        this.name = name;
        this.password = password;
    }

    public String getCourseName() {
        return null;
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

    public void setGrades(Student student, Course course, int grade) {

    }

    public void saveGrade(Student student, int pvl, int result) {

    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }

    public void enroll(Course course) {
        Database database = new Database();
        database.enrollStudent(this, course.getCourseID());
        database.close();
    }


    public void viewGrades() {
        Database database = new Database();
        database.viewGrades(this);
        database.close();
    }
}

