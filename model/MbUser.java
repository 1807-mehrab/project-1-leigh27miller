package mb.model;

public class MbUser {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean admin;

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        if("Y".equalsIgnoreCase(admin)){
            this.admin = true;
        } else {
            this.admin = false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MbUser() {

    }
}
