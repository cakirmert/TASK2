class SystemFactory {
    public static Course createCourse(String courseType, String courseName, int credits, Professor professor) {
        if (courseType.equalsIgnoreCase("generic")) {
            return new Course(courseName, credits, professor);
        } else if (courseType.equalsIgnoreCase("lab")) {
            return new Lab(courseName + " Lab", credits, professor);
        } else {
            throw new IllegalArgumentException("Invalid course type: " + courseType);
        }
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
