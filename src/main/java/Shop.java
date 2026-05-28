import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private User user;
    private final List<UserPattern> users = new ArrayList<>();
    private final String usersDB = "users_db.txt";

    public Shop() {
        ComplectionUsersVector();
    }

    public void start() {
        System.out.println("\n\n\n\t\t\t===| Добро пожаловать в технический магазин Azazin! |===\n\n\n");
        Login();
    }

    public void Login() {
        String login, pass, choose;

        while (true) {
            System.out.print("Введите логин -> ");
            login = ConsoleIO.getline();
            System.out.print("Введите пароль -> ");
            pass = ConsoleIO.getline();

            if ("exit".equals(login) && "exit".equals(pass)) {
                return;
            }

            for (UserPattern i : users) {
                if (login.equals(i.GetLogin()) && pass.equals(i.GetPass())) {
                    if ("SuperAdmin".equals(i.GetStatus())) {
                        user = new SuperAdmin(i.GetIdUser(), i.GetLogin(), i.GetPass());
                        ConsoleUtil.clear();
                        System.out.println("Добро пожаловать, " + i.GetLogin() + "\n");
                        System.out.println("Ваш статус: " + i.GetStatus() + "\n");

                        while (true) {
                            System.out.print("Выберите тип склада..\n1 - Готовый\n2 - Новый\nВвод -> ");
                            choose = ConsoleIO.getline();
                            if ("1".equals(choose)) {
                                user.getOfficial().getStorage().CreateStorage();
                                ConsoleUtil.clear();
                                break;
                            } else if ("2".equals(choose)) {
                                user.getOfficial().getStorage().CreateNewStorege();
                                ConsoleUtil.clear();
                                break;
                            } else {
                                ConsoleIO.err();
                            }
                        }

                        ConsoleUtil.sleep(1500);
                        user.ShowMenu();
                        return;
                    } else {
                        if ("Admin".equals(i.GetStatus())) user = new Admin(i.GetIdUser(), i.GetLogin(), i.GetPass());
                        else if ("Employee".equals(i.GetStatus())) user = new Employee(i.GetIdUser(), i.GetLogin(), i.GetPass());

                        ConsoleUtil.clear();
                        System.out.println("Добро пожаловать, " + user.getLogin() + "\n");
                        System.out.println("Ваш статус: " + user.getStatus() + "\n");
                        ConsoleUtil.sleep(1500);
                        user.ShowMenu();
                        return;
                    }
                }
            }
            ConsoleIO.err();
        }
    }

    public void ComplectionUsersVector() {
        users.clear();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(usersDB), StandardCharsets.UTF_8))) {
            String userLine;
            while ((userLine = br.readLine()) != null) {
                userLine = userLine.trim();
                if (userLine.isEmpty()) break;
                String[] parts = userLine.split("\\s+");
                if (parts.length < 3) continue;
                String login = parts[0];
                String password = parts[1];
                String status = parts[2];
                users.add(new UserPattern(users.size(), login, password, status));
            }
        } catch (Exception e) {
            System.out.println("Дата база пользователей не открыта!");
        }
    }
}

