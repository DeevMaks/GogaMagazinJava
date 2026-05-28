import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final Numbers numbersFoo = new Numbers();
    private final List<UserPattern> users = new ArrayList<>();
    private final String usersDB = "users_db.txt";

    public Account() {
        ComplectionUsersVector();
    }

    public void ChangeUserAccount(String status) {
        String choose;

        while (true) {
            ConsoleUtil.clear();
            System.out.println("1 - Добавить нового пользователя");
            System.out.println("2 - Показать пользователей");
            System.out.println("3 - Изменить пароль пользователю");
            System.out.println("4 - Удаление учетной записи");
            System.out.println("0 - Выход из редактора аккунтов");
            System.out.print("Ввод -> ");
            choose = ConsoleIO.getline();
            if ("1".equals(choose)) {
                AddNewUser();
            } else if ("2".equals(choose) && users.size() > 1) {
                ShowUsers(status);
            } else if ("3".equals(choose) && users.size() > 1) {
                ChangePass(status);
            } else if ("4".equals(choose) && users.size() > 1) {
                DeleteUser(status);
            } else if ("0".equals(choose)) {
                ConsoleUtil.clear();
                break;
            } else {
                ConsoleIO.err();
            }
        }
    }

    public void AddNewUser() {
        String newLogin, newPass = "", newRole = "", choose;
        boolean exit = true;

        while (exit) {
            while (true) {
                ConsoleUtil.clear();
                System.out.print("Введите логин нового пользователя или \"exit\" для выхода -> ");
                newLogin = ConsoleIO.getline();
                if ("exit".equals(newLogin)) {
                    System.out.println("Отмена добавления нового пользователя!");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }
                if (CheckLogin(newLogin)) break;
                System.out.println("Допустимые символы: a-z, A-Z, 0-9\n");
                ConsoleUtil.sleep(1500);
            }

            while (exit) {
                ConsoleUtil.clear();
                System.out.print("Введите пароль нового пользователя или \"exit\" для выхода -> ");
                newPass = ConsoleIO.getline();
                if ("exit".equals(newPass)) {
                    System.out.println("Отмена добавления нового пользователя!");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }
                if (CheckPass(newPass)) break;
                System.out.println("Допустимые символы: a-z, A-Z, 0-9 + символы\n");
                ConsoleUtil.sleep(1500);
            }

            while (exit) {
                ConsoleUtil.clear();
                System.out.println("Выберите роль для нового пользователя или \"exit\" для выхода:");
                System.out.print("1 - Администратор\n2 - Сотрудник\nВвод -> ");
                choose = ConsoleIO.getline();
                if ("exit".equals(choose)) {
                    System.out.println("Отмена добавления нового пользователя!");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }
                if ("1".equals(choose)) {
                    newRole = "Admin";
                    break;
                } else if ("2".equals(choose)) {
                    newRole = "Employee";
                    break;
                } else {
                    ConsoleIO.err();
                }
            }

            while (exit) {
                ConsoleUtil.clear();
                System.out.println("Пользователь -> " + newLogin);
                System.out.println("Пароль -> " + newPass);
                System.out.println("Роль -> " + newRole);

                System.out.print("Подтвердить?\n1 - Да\t2 - Нет\nВвод -> ");
                choose = ConsoleIO.getline();
                if ("1".equals(choose)) {
                    users.add(new UserPattern(users.size(), newLogin, newPass, newRole));
                    System.out.print("Идет подготовка...");
                    ConsoleUtil.sleep(1500);
                    SaveToDataBase();
                    System.out.println("Пользователь успешно добавлен!");
                    exit = false;
                    ConsoleUtil.sleep(1500);
                    break;
                } else if ("2".equals(choose)) {
                    System.out.println("Отмена");
                    ConsoleUtil.sleep(1500);
                    break;
                } else {
                    ConsoleIO.err();
                }
            }
        }
    }

    public void ShowUsers(String status) {
        ConsoleUtil.clear();
        System.out.println("№\t" + String.format("%-12s", "Логин\t\t") + "Пароль\t\t\t" + "Роль");

        for (UserPattern i : users) {
            if ("Admin".equals(status) && "SuperAdmin".equals(i.GetStatus())) continue;
            System.out.printf("%d\t%-8s\t\t%s\t\t\t%s\n",
                    i.GetIdUser(),
                    i.GetLogin(),
                    i.GetPass(),
                    i.GetStatus());
        }
        ConsoleUtil.pause();
    }

    public void ChangePass(String status) {
        String oldPass, newPass, choose;

        while (true) {
            ShowUsers(status);
            System.out.print("Выберите номер пользователя или \"exit\" для выхода -> ");
            choose = ConsoleIO.getline();

            if ("exit".equals(choose)) break;

            if (numbersFoo.IsNumber(choose)) {
                int id = Integer.parseInt(choose);

                for (UserPattern i : users) {
                    if (i.GetIdUser() == id && !"SuperAdmin".equals(i.GetStatus())) {
                        System.out.print("Изменить пароль пользователя?\nДа - 1\tНет - 2 ->");
                        choose = ConsoleIO.getline();

                        if ("1".equals(choose)) {
                            while (true) {
                                System.out.print("Введите старый пароль -> ");
                                oldPass = ConsoleIO.getline();
                                System.out.print("Введите новый пароль -> ");
                                newPass = ConsoleIO.getline();

                                if (oldPass.equals(i.GetPass())) {
                                    i.SetPass(newPass);
                                    SaveToDataBase();
                                    System.out.println("Пароль изменен!");
                                    ConsoleUtil.sleep(1500);
                                    break;
                                } else {
                                    ConsoleIO.err();
                                }
                            }
                        } else if ("2".equals(choose)) {
                            break;
                        } else {
                            ConsoleIO.err();
                            ConsoleUtil.sleep(1500);
                            break;
                        }
                    }
                }
            }
            return;
        }
    }

    public boolean CheckLogin(String str) {
        if (str.length() < 5 || str.length() >= 20) {
            System.out.println("Недопустимая длина логина! От 5 до 20");
            ConsoleUtil.sleep(1500);
            return false;
        }

        for (int k = 0; k < str.length(); k++) {
            char sym = str.charAt(k);
            if (!numbersFoo.getSpesialSymbols().contains(sym)) {
                System.out.println("Неккоретный символ в логине!");
                ConsoleUtil.sleep(1500);
                return false;
            }
        }

        for (UserPattern i : users) {
            if (str.equals(i.GetLogin())) {
                System.out.println("Имя уже занято!\n");
                ConsoleUtil.sleep(1500);
                return false;
            }
        }
        return true;
    }

    public void DeleteUser(String status) {
        String chooseId, choose;

        while (true) {
            if (users.size() < 2 && "SuperAdmin".equals(status)) {
                System.out.println("Нету пользователей для удаления!");
                ConsoleUtil.sleep(2000);
                break;
            }

            while (true) {
                ShowUsers(status);
                System.out.print("Введите ID пользователя для удаления -> ");
                chooseId = ConsoleIO.getline();

                if (numbersFoo.IsNumber(chooseId)) {
                    int id = Integer.parseInt(chooseId);
                    for (int idx = 0; idx < users.size(); idx++) {
                        UserPattern i = users.get(idx);
                        if (i.GetIdUser() == id && !"SuperAdmin".equals(i.GetStatus())) {
                            System.out.print("Вы действительно хотите удалить, " + i.GetLogin() + "?\nДа - 1\tНет - 2 -> ");
                            choose = ConsoleIO.getline();
                            if ("1".equals(choose)) {
                                System.out.println("Удаление пользователя...");
                                users.remove(idx);
                                SaveToDataBase();
                                System.out.println("Пользователь успешно удален!");
                                ConsoleUtil.sleep(2000);
                                break;
                            } else if ("2".equals(choose)) {
                                break;
                            } else {
                                ConsoleIO.err();
                                ConsoleUtil.sleep(1500);
                                break;
                            }
                        }
                    }
                } else {
                    ConsoleIO.err();
                    ConsoleUtil.sleep(1500);
                }
            }
        }
    }

    public boolean CheckPass(String str) {
        if (str.length() < 8 || str.length() > 30) {
            System.out.println("Ошибка длины пароля");
            ConsoleUtil.sleep(1500);
            return false;
        }

        int numCount = 0, sumCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char sym = str.charAt(i);
            if (!numbersFoo.getPassSymbols().contains(sym)) {
                System.out.println("Неккоретный ввод");
                ConsoleUtil.sleep(1500);
                return false;
            }

            if (Character.isDigit(sym)) numCount++;
            if (numbersFoo.getPassSymbols().contains(sym)) sumCount++;
        }

        if (numCount > 2 && sumCount > 2) {
            return true;
        } else {
            System.out.println("Минимум 3 символа и 3 цифры");
            return false;
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
                String st = parts[2];
                users.add(new UserPattern(users.size(), login, password, st));
            }
        } catch (Exception e) {
            System.out.println("Дата база пользователей не открыта!");
        }
    }

    public void SaveToDataBase() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(usersDB), StandardCharsets.UTF_8))) {
            for (UserPattern i : users) {
                bw.write(i.GetLogin() + " " + i.GetPass() + " " + i.GetStatus());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("База данных не открылась!");
        }
    }
}

