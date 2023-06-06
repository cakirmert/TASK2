import java.sql.*;

// Database class
class Database implements ControlledObject {
    private Connection connection;

    public Database() {
        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stisys", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int saveStudent(Student student) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getPassword());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int saveCourse(Course course) {
        String sql = "INSERT INTO course (course_name, credits) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredits());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void saveGrade(Student student, Course course, int pvl, int result) {
        String sql = "UPDATE results SET pvl = ?, grade = ? WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pvl);
            pstmt.setInt(2, result);
            pstmt.setInt(3, student.getId());
            pstmt.setInt(4, course.getCourseID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPVL(Student student, Course course, boolean pvl) {
        int pvlValue = pvl ? 1 : 0;
        String sql = "UPDATE results SET pvl = ? WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pvlValue);
            pstmt.setInt(2, student.getId());
            pstmt.setInt(3, course.getCourseID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public int getId() {
        return 0;
    }
}
