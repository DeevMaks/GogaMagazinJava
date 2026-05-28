public class SuperAdmin extends User {
    public SuperAdmin(int id, String login, String password) {
        super(id, login, password);
        status = "SuperAdmin";
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
            System.out.println("5 - Измнить цену");
            System.out.println("6 - Редактиривать склад");
            System.out.println("7 - Редактиривать персонал");
            System.out.println("8 - Отчет о прибыли");
            System.out.println("0 - Закрыть смену");
            System.out.print("Ввод -> ");
            choose = ConsoleIO.getline();

            if ("1".equals(choose) && official.getStorage().getStotageSize() > 0) {
                getOfficial().getSale().Selling();
            } else if ("2".equals(choose) && official.getStorage().getStotageSize() > 0) {
                getOfficial().getStorage().ShowStorage(0);
            } else if ("3".equals(choose) && official.getStorage().getStotageSize() > 0) {
                getOfficial().getStorage().AddNewItem();
            } else if ("4".equals(choose) && official.getStorage().getStotageSize() > 0) {
                getOfficial().getStorage().RemoveStorageItem();
            } else if ("5".equals(choose) && official.getStorage().getStotageSize() > 0) {
                getOfficial().getStorage().ChengePrice();
            } else if ("6".equals(choose)) {
                official.getStorage().ChengeStorage();
            } else if ("7".equals(choose)) {
                official.getAccount().ChangeUserAccount(status);
            } else if ("8".equals(choose)) {
                getOfficial().getSale().ShowIncome();
            } else if ("0".equals(choose)) {
                if (Logout()) break;
                ConsoleUtil.clear();
            } else {
                ConsoleIO.err();
            }
        }
    }
}

