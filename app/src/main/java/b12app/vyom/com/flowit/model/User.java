package b12app.vyom.com.flowit.model;

public class User {

    private int mobile;
    private String name, type, email, password;

    public User(int mobile, String name, String type, String email, String password) {
        this.mobile = mobile;
        this.name = name;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    //getters
    public int getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }


    //setters
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }
}
