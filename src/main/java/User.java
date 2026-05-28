public abstract class User {
    protected String login = "";
    protected String password = "";
    protected String status = "";
    protected int id = 0;
    protected Official official = new Official();

    public User() {}

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public abstract void ShowMenu();

    public boolean Logout() {
        String choose, pass;
        while (true) {
            ConsoleUtil.clear();
            System.out.print("Введите пароль или \"exit\" для возращения в меню\nВвод -> ");
            choose = ConsoleIO.getline();

            if ("exit".equals(choose)) {
                return false;
            }

            System.out.print("Введите пароль еще раз для подтверждения или \"exit\" для возращения в меню\nВвод -> ");
            pass = ConsoleIO.getline();

            if (choose.equals(pass) && pass.equals(this.getPassword())) {
                return true;
            } else {
                System.out.println("Неверный пароль!");
                ConsoleUtil.sleep(2000);
            }
        }
    }

    public Official getOfficial() {
        return official;
    }
}

