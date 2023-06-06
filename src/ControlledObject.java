interface ControlledObject {
    String getCourseName();
    int getCredits();
    void enrollStudent(Student student);
    void displayCourseInfo();

    int getCourseID();
    void setGrades(Student student, Course course, int grade);
    void saveGrade(Student student, int pvl, int result);

    void setId(int id);
}
