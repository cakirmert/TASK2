class SystemFactory {
    public static Course createCourse(String courseType, String courseName, int credits, Professor professor, Course course) {
        if (courseType.equalsIgnoreCase("generic")) {
            return new Course(courseName, credits, professor);
        } else if (courseType.equalsIgnoreCase("lab")) {
            Lab lab = new Lab(courseName, credits, professor);
            course.setLab(lab);
            return lab;
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
