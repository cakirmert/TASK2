package de.haw.stisys;

/**
 * The Student class represents a student that extends the User class.
 */
class Student extends User {


    private final String name;
    private final String password;
    private int id;

    public Student(String name, String password) {
        super(name,password);
        this.name = name;
        this.password = password;
    }

    /**
     * Enrolls the student in a course.
     * @param course The course to enroll in.
     */
    public void enroll(Course course) {
        Database database = new Database();
        database.enrollStudent(this, course);
        database.close();
    }
    
    /**
     * Enrolls the student in a lab.
     * @param lab The lab to enroll in.
     */
    public void enrolllab(Lab lab) {
        Database database = new Database();
        database.enrollStudent(this, lab);
        database.close();
    }

    /**
     * Views the grades of the student.
     */

    public void viewGrades() {
        Database database = new Database();
        database.viewGrades(this);
        database.close();
    }

    // Implemented any additional methods from User class and therefore ControlledObject interface.

    public String getCourseName() {
        return null;
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
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }

}

