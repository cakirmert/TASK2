import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Student class
class Student extends User implements ControlledObject {


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

    public void viewGrades() {
        Database database = new Database();
        String sql = "SELECT course_id, pvl, result FROM results WHERE student_id = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Course: " + rs.getInt("course_id"));
                System.out.println("PVL: " + rs.getInt("pvl"));
                System.out.println("Result: " + rs.getInt("result"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

