import java.sql.PreparedStatement;
import java.sql.SQLException;

// GenericCourse class implementing ControlledObject interface
class Course implements ControlledObject {
    private final String courseName;
    private final int credits;
    private final Professor professor;
    private int courseID;

    public Course(String courseName, int credits, Professor professor) {
        this.courseName = courseName;
        this.credits = credits;
        this.professor = professor;
    }

    public void enrollStudent(Student student) {
        Database database = new Database();
        String sql = "INSERT INTO results (student_id, course_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, this.courseID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveGrade(Student student, int pvl, int result) {
        Database database = new Database();
        String sql = "UPDATE results SET pvl = ?, result = ? WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, pvl);
            pstmt.setInt(2, result);
            pstmt.setInt(3, student.getId());
            pstmt.setInt(4, this.courseID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();
    }
    
    public void displayCourseInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course ID: " + courseID);
        System.out.println("Credits: " + credits);
        System.out.println("Professor: " + professor.getName());
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getCourseID() {
        return this.courseID;
    }

    public void setGrades(Student student, Course course, int grade) {

    }

    public int getCredits() {
        return this.credits;
    }
    public void setId(int id) {
        this.courseID = id;
    }

    @Override
    public int getId() {
        // This method is only needed for the AccessControlProxy
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

}
