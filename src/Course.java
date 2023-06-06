// Course interface
interface Course extends ControlledObject {
    void enrollStudent(Student student);

    void displayCourseInfo();

    String getCourseName();

    int getCredits();

    int getCourseID();
    void saveGrade(Student student, int pvl, int result);
    void setId(int id);
}
