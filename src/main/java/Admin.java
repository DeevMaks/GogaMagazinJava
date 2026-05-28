public class Admin extends User {
    public Admin(int id, String login, String password) {
        super(id, login, password);
        status = "Admin";
    }

    @Override
    public void ShowMenu() {
        String choose;

        while (true) {
            ConsoleUtil.clear();

            System.out.println("1 - Начать продажу");
            System.out.println("2 - Показать склад");
            System.out.println("3 - Пополнить склад");
            System.out.println("4 - Списать товар");
            System.out.println("5 - Редактиривать склад");
            System.out.println("6 - Редактиривать персонал");
            System.out.println("7 - Отчет о прибыли");
            System.out.println("0 - Закрыть смену");
            System.out.print("Ввод -> ");
            choose = ConsoleIO.getline();

            if ("1".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getSale().Selling();
            } else if ("2".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getStorage().ShowStorage(0);
            } else if ("3".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getStorage().AddStorageItem();
            } else if ("4".equals(choose) && official.getStorage().getStotageSize() > 0) {
                official.getStorage().RemoveStorageItem();
            } else if ("5".equals(choose)) {
                official.getStorage().ChengeStorage();
            } else if ("6".equals(choose)) {
                official.getAccount().ChangeUserAccount("Admin");
            } else if ("7".equals(choose)) {
                official.getSale().ShowIncome();
            } else if ("0".equals(choose)) {
                if (Logout()) break;
                ConsoleUtil.clear();
            } else {
                ConsoleIO.err();
            }
        }
    }
}

