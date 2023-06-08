/**
 * The ControlledObject interface defines the operations that controlled objects must implement.
 * It is used as a common interface for different controlled objects.
 */
interface ControlledObject {
    String getCourseName();
    int getCredits();
    void displayCourseInfo();
    int getCourseID();
    void setGrades(Student student, Course course, int grade);
    void setId(int id);
}
