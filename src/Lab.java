import java.sql.PreparedStatement;
import java.sql.SQLException;

// LabCourse class implementing Course interface
class Lab implements Course {
    private final String courseName;
    private final int credits;
    private final Professor professor;
    private int courseID;
    
    public Lab(String courseName, int credits, Professor professor) {
        this.courseName = courseName;
        this.credits = credits;
        this.professor = professor;
    }

    @Override
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
        database.close();
    }

    @Override
    public void displayCourseInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course ID: " + courseID);
        System.out.println("Credits: " + credits);
        System.out.println("Professor: " + professor.getName());
    }
    @Override
    public String getCourseName() {
        return this.courseName;
    }

    @Override
    public int getCourseID() {
        return this.courseID;
    }

    @Override
    public void setGrades(Student student, Course course, int grade) {

    }

    @Override
    public void saveGrade(Student student, int pvl, int result) {

    }

    @Override
    public int getCredits() {
        return this.credits;
    }
    @Override
    public void setId(int id) {
        this.courseID = id;
    }

    @Override
    public int getId() {
        return 0;
    }
}
