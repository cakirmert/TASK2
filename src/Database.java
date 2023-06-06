import java.sql.*;

// Database class
class Database implements ControlledObject {
    
    private Connection connection;

    public Database() {
        // Initialize the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void enrollStudent(Student student, int courseId) {
        String sql = "INSERT INTO results (student_id, course_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayCourseInfo(int courseId) {
        String sql = "SELECT course_name, credits, professor_name FROM course WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String courseName = rs.getString("course_name");
                int credits = rs.getInt("credits");
                String professorName = rs.getString("professor_name");

                System.out.println("Course Name: " + courseName);
                System.out.println("Course ID: " + courseId);
                System.out.println("Credits: " + credits);
                System.out.println("Professor: " + professorName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int saveCourse(Course course) {
        String sql = "INSERT INTO course (course_name, credits, professor_name) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredits());
            pstmt.setString(3, course.getProfessor());
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

    public void saveGrade(Student student, Course course, int result) {
        String sql = "SELECT pvl FROM results WHERE student_id = ? AND course_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, course.getCourseID());
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                Integer pvl = rs.getInt("pvl");
                
                if (pvl == 0) {
                    System.out.println("Error: Student didn't pass the lab.");
                } else if (pvl == 1 || pvl == null) {
                    String updateSql = "UPDATE results SET pvl = ?, grade = ? WHERE student_id = ? AND course_id = ?";
                    try (PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {
                        updatePstmt.setInt(1, pvl);
                        updatePstmt.setInt(2, result);
                        updatePstmt.setInt(3, student.getId());
                        updatePstmt.setInt(4, course.getCourseID());
                        updatePstmt.executeUpdate();
                    }
                } else {
                    System.out.println("PVL is not set. Grade cannot be set.");
                }
            } else {
                System.out.println("No result found for the student and course.");
            }
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
    

    public void viewGrades(Student student) {
        String sql = "SELECT results.course_id, results.pvl, results.grade, course.course_name " +
                     "FROM results " +
                     "INNER JOIN course ON results.course_id = course.id " +
                     "WHERE results.student_id = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            ResultSet rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                Short pvl = rs.getShort("pvl");
                String courseName = rs.getString("course_name");
    
                System.out.println("Course: " + courseName);
                System.out.println("Course ID: " + courseId);
                System.out.println("PVL: " + (pvl == 0 ? "Fail" : pvl == 1 ? "Pass" : "Not Set"));

                if (pvl == 2) {
                    int grade = rs.getInt("grade");
                    System.out.println("Grade: " + grade);
                }
                
    
                System.out.println();
            }
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


    public void setId(int id) {

    }

    public int getId() {
        return 0;
    }


}
