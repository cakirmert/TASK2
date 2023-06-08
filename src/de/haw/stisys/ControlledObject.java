package de.haw.stisys;
/**
 * The ControlledObject interface defines the operations that controlled objects must implement.
 * It is used as a common interface for different controlled objects.
 */
public interface ControlledObject {
    String getCourseName();
    int getCredits();
    void displayCourseInfo();
    int getCourseID();
    void setId(int id);
}
