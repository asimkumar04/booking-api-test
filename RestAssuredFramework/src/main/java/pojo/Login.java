package pojo;

public class Login {

    private String username;
    private String password;

    // Constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter methods (required for serialization)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
