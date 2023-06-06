abstract class User implements ControlledObject {
    private final String name;
    private int id;
    private final String password;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
