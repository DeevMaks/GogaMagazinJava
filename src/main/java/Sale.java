public class Sale {
    private Storage storage;
    private final Check check = new Check();
    private final Numbers number = new Numbers();
    private double cash = 100000;
    private double income = 0;

    public Sale() {}

    public Sale(Storage st) {
        this.storage = st;
        this.cash = 0.0;
        this.income = 0.0;
    }

    public boolean sellProduct(int productId, int quantity) {
        if (quantity == 0 || storage == null) return false;
        Product p = storage.findProduct(productId);
        if (p == null) return false;
        if (p.getCount() < quantity) return false;

        p.setCount(p.getCount() - quantity);

        Product soldItem = new Product(p.getName(), quantity, p.getIDproduct(), p.getPrice());
        check.addItem(soldItem);

        double cost = soldItem.getPrice() * quantity;
        cash += cost;
        income += cost;

        storage.UpdateDataBase();
        return true;
    }

    public void closeCheck() {
        check.print();
        check.clear();
    }

    public double getCash() {
        return cash;
    }

    public double getIncome() {
        return income;
    }

    public void setStorage(Storage st) {
        storage = st;
    }

    public void Selling() {
        if (storage == null) {
            System.out.println("Ошибка: склад не задан.");
            return;
        }

        if (storage.ProductsVector().isEmpty()) {
            System.out.println("Склад пуст. Продажа невозможна.");
            return;
        }

        String chooseid, chooseCount;

        while (true) {
            ConsoleUtil.clear();
            storage.ShowStorage(0);

            System.out.println("=== Продажа ===");
            System.out.print("Введите ID товара или \"exit\" для выхода: ");

            chooseid = ConsoleIO.getline();

            if ("exit".equals(chooseid)) {
                if (check.getSize() == 0) {
                    System.out.println("Отмена покупок!");
                    check.clear();
                    return;
                }

                int total = check.print();
                String chooseMoney;

                while (true) {
                    System.out.print("Введите кол-во наличных или \"exit\" для отмены покупок: ");
                    chooseMoney = ConsoleIO.getline();

                    if ("exit".equals(chooseMoney)) {
                        // Возврат товара на склад (упрощенно, как в оригинале).
                        for (Product j : check.VectorItems()) {
                            for (Product stItem : storage.ProductsVector()) {
                                if (j.getName().equals(stItem.getName())) {
                                    stItem.setCount(stItem.getCount() + j.getCount());
                                }
                            }
                        }
                        System.out.println("Отмена покупок!");
                        ConsoleUtil.sleep(2000);
                        return;
                    }

                    if (number.IsNumber(chooseMoney)) {
                        int money = Integer.parseInt(chooseMoney);

                        if (money - total > cash) {
                            System.out.println("Введите число меньше! Сдачи не хватает");
                            ConsoleUtil.sleep(2000);
                        } else {
                            cash += total;
                            cash -= money - total;
                            System.out.println("Оплата завершена! Ваша сдача: " + (money - total));
                            ConsoleUtil.pause();
                            return;
                        }
                    }
                }
            }

            System.out.print("Введите кол-во товара: ");
            chooseCount = ConsoleIO.getline();

            if (number.IsNumber(chooseid) && number.IsNumber(chooseCount)) {
                int id = Integer.parseInt(chooseid);
                int count = Integer.parseInt(chooseCount);
                boolean exit = true;

                // Попытка добавить к существующей позиции чека
                for (Product i : check.VectorItems()) {
                    Product item = storage.ProductsVector().get(id - 1);
                    if (i.getName().equals(item.getName())) {
                        if (count > 0 && count < item.getCount()) {
                            i.setCount(i.getCount() + count);
                            item.setCount(item.getCount() - count);
                            System.out.println("Товар пополнен!");
                            ConsoleUtil.sleep(2000);
                            exit = false;
                            break;
                        } else {
                            System.out.println("Такого кол-во нету на складе!");
                            ConsoleUtil.sleep(2000);
                            exit = false;
                            break;
                        }
                    }
                }

                if (exit) {
                    for (Product i : storage.ProductsVector()) {
                        if (id == i.getIDproduct()) {
                            if (count < i.getCount() && count > 0) {
                                Product itemTemp = new Product();
                                itemTemp.setIDproduct(check.VectorItems().size());
                                itemTemp.setName(i.getName());
                                itemTemp.setPrice(i.getPrice());
                                itemTemp.setCount(count);
                                check.addItem(itemTemp);

                                i.setCount(i.getCount() - count);
                                System.out.println("Товар добавлен в чек!");
                                ConsoleUtil.sleep(2000);
                                exit = false;
                                break;
                            } else {
                                System.out.println("Введенное кол-во привышает кол-во товара на складе или число < 0!");
                                ConsoleUtil.pause();
                                exit = false;
                                break;
                            }
                        }
                    }
                }

                if (exit) {
                    System.out.println("Товар не найден!");
                    ConsoleUtil.sleep(2000);
                }
            }
        }
    }

    public void ShowIncome() {
        System.out.println("Общая выручка: " + (int) income + " руб.");
    }

    public void StoreReturner() {
        System.out.println("Модуль возврата в разработке.");
    }
}

