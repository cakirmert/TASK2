abstract class CourseFactory implements ControlledObject {
    public static Course createCourse(String courseType, String courseName, int credits, Professor professor) {
        // Logic to create the specific course type based on courseType parameter
        if (courseType.equalsIgnoreCase("generic")) {
            return new GenericCourse(courseName, credits, professor);
        } else if (courseType.equalsIgnoreCase("lab")) {
            return new LabCourse(courseName+" Lab", credits, professor);
        } else {
            throw new IllegalArgumentException("Invalid course type: " + courseType);
        }
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

    }

    @Override
    public int getId() {
        return 0;
    }
}
