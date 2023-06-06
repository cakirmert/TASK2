interface ControlledObject {
    String getCourseName();
    int getCredits();
    void enrollStudent(Student student);
    void displayCourseInfo();
    int getCourseID();
    void setGrades(Student student, Course course, int grade);
    void setId(int id);
}
