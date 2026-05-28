public class Employee extends User {
    public Employee(int id, String login, String password) {
        super(id, login, password);
        status = "Employee";
    }

    @Override
    public void ShowMenu() {
        String choose;

        while (true) {
            ConsoleUtil.clear();

            System.out.println("1 - Начать продажу");
            System.out.println("2 - Показать склад");
            System.out.println("3 - Отчет о прибыли");
            System.out.println("0 - Закрыть смену");
            System.out.print("Ввод -> ");
            choose = ConsoleIO.getline();

            if ("1".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getSale().Selling();
            } else if ("2".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getStorage().ShowStorage(0);
            } else if ("3".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getSale().getIncome();
            } else if ("0".equals(choose)) {
                if (Logout()) break;
                ConsoleUtil.clear();
            } else {
                ConsoleIO.err();
            }
        }
    }
}

