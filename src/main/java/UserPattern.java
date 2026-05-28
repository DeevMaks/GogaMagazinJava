public class UserPattern {
    private int id = 0;
    private String login = "";
    private String pass = "";
    private String status = "";

    public UserPattern(int id, String login, String pass, String status) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.status = status;
    }

    public void setIdUser(int id) {
        this.id = id;
    }

    public void SetLogin(String login) {
        this.login = login;
    }

    public void SetPass(String pass) {
        this.pass = pass;
    }

    public void SetStatus(String status) {
        this.status = status;
    }

    public int GetIdUser() {
        return id;
    }

    public String GetLogin() {
        return login;
    }

    public String GetPass() {
        return pass;
    }

    public String GetStatus() {
        return status;
    }
}

