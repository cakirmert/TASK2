/**
 * The SystemFactory class provides factory methods for creating various objects in the system.
 * It implements the Factory Method design pattern.
 */
class SystemFactory {
    public static Course createCourse(String courseName, int credits, Professor professor) {
            return new Course(courseName, credits, professor);
    }
    public static Lab createLab(String labName, int credits, Professor professor, Course course) {
        Lab lab = new Lab(labName, credits, professor, course);
        course.setLab(lab);
        return lab;
    }
    

    public static Student createStudent(String name, String password) {
        return new Student(name, password);
    }

    public static Professor createProfessor(String name, String password) {
        return new Professor(name, password);
    }

    public static Database createDatabase() {
        return new Database();
    }
}
