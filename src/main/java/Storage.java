import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final int maxItemSize = 150;
    private final List<Product> products = new ArrayList<>();
    private String fileProducts = "Storage.txt";
    private final Numbers numbers = new Numbers();

    public Storage() {}

    public void CreateStorage() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileProducts), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length < 4) continue;
                Product item = new Product();
                try {
                    item.setIDproduct(Integer.parseInt(parts[0]));
                    item.setName(parts[1]);
                    item.setCount(Integer.parseInt(parts[2]));
                    item.setPrice(Double.parseDouble(parts[3]));
                } catch (NumberFormatException ignored) {
                    continue;
                }
                if (item.getIDproduct() != 0) products.add(item);
            }
        } catch (Exception e) {
            System.out.println("База данных не открыта!");
        }
    }

    public void CreateNewStorege() {
        System.out.print("Введите новое имя для склада -> ");
        String file = ConsoleIO.getline();
        fileProducts = file + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileProducts), StandardCharsets.UTF_8))) {
            System.out.println("Склад успешно создан!");
            ConsoleUtil.pause();
            ConsoleUtil.clear();
        } catch (Exception e) {
            System.out.println("Новая база данных не открылась!");
        }
    }

    public void AddStorageItem() {
        String chooseId, chooseCount, choose;
        int id, count;

        while (true) {
            ConsoleUtil.clear();
            ShowStorage(1);
            System.out.print("Введите ID товара или \"exit\" для выхода -> ");
            chooseId = ConsoleIO.getline();

            if ("exit".equals(chooseId)) {
                System.out.println("Отмена перации пополнения склада!");
                ConsoleUtil.sleep(1500);
                ConsoleUtil.clear();
                break;
            }

            System.out.print("Введите кол-во товара для пополнения -> ");
            chooseCount = ConsoleIO.getline();

            if (numbers.IsNumber(chooseId) && numbers.IsNumber(chooseCount)) {
                id = Integer.parseInt(chooseId) - 1;
                count = Integer.parseInt(chooseCount);

                if (id < 0 || id > products.size() - 1 || count < 0 || count > maxItemSize) {
                    System.out.println("Неккоретный ID или кол-во\nМаксвимальное кол-во - " + maxItemSize);
                    ConsoleUtil.sleep(1500);
                } else {
                    Product p = products.get(id);
                    System.out.printf("%-25s\t%d -----> %d\n\n", p.getName(), p.getCount(), p.getCount() + count);
                    System.out.print("Подтверить?\n Да - 1\t Нет - 2\nВвод -> ");
                    choose = ConsoleIO.getline();
                    if ("1".equals(choose)) {
                        p.setCount(p.getCount() + count);
                        UpdateDataBase();
                        System.out.println("Товар успешно поплнен!\n");
                        ConsoleUtil.sleep(1500);
                        ConsoleUtil.clear();
                        break;
                    } else if ("2".equals(choose)) {
                        System.out.println("Отмена операции!");
                        ConsoleUtil.sleep(1500);
                    } else {
                        ConsoleIO.err();
                    }
                }
            }
        }
    }

    public void RemoveStorageItem() {
        String chooseId, chooseCount, choose;
        int id, count;

        while (true) {
            ConsoleUtil.clear();
            ShowStorage(1);
            System.out.print("Введите ID товара или \"exit\" для выхода -> ");
            chooseId = ConsoleIO.getline();

            if ("exit".equals(chooseId)) {
                System.out.println("Отмена перации списания склада!");
                ConsoleUtil.sleep(1500);
                ConsoleUtil.clear();
                break;
            }

            System.out.print("Введите кол-во товара для списания -> ");
            chooseCount = ConsoleIO.getline();

            if (numbers.IsNumber(chooseId) && numbers.IsNumber(chooseCount)) {
                id = Integer.parseInt(chooseId) - 1;
                count = Integer.parseInt(chooseCount);

                if (id < 0 || id > products.size() - 1 || count < 0 || count > maxItemSize || count > products.get(id).getCount()) {
                    System.out.println("Неккоретный ID или кол-во\nМаксвимальное кол-во - " + maxItemSize);
                    ConsoleUtil.sleep(1500);
                } else {
                    Product p = products.get(id);
                    System.out.printf("%-25s\t%d -----> %d\n\n", p.getName(), p.getCount(), p.getCount() - count);
                    System.out.print("Подтверить?\n Да - 1\t Нет - 2\nВвод -> ");
                    choose = ConsoleIO.getline();
                    if ("1".equals(choose)) {
                        p.setCount(p.getCount() - count);
                        UpdateDataBase();
                        System.out.println("Товар успешно списанан!\n");
                        ConsoleUtil.sleep(1500);
                        ConsoleUtil.clear();
                        break;
                    } else if ("2".equals(choose)) {
                        System.out.println("Отмена операции!");
                        ConsoleUtil.sleep(1500);
                    } else {
                        ConsoleIO.err();
                    }
                }
            }
        }
    }

    public void ChengePrice() {
        String chooseId, choosePrice, choose;
        int id;
        double newPrice;

        while (true) {
            ConsoleUtil.clear();
            ShowStorage(2);
            System.out.print("Введите ID товара или \"exit\" для выхода -> ");
            chooseId = ConsoleIO.getline();

            if ("exit".equals(chooseId)) {
                System.out.println("Отмена перации изменения цены!");
                ConsoleUtil.sleep(1500);
                ConsoleUtil.clear();
                break;
            }

            System.out.print("Введите новую цену для товара -> ");
            choosePrice = ConsoleIO.getline();

            if (numbers.IsNumber(chooseId) && numbers.IsNumber(choosePrice)) {
                id = Integer.parseInt(chooseId) - 1;
                newPrice = Double.parseDouble(choosePrice);

                if (id < 0 || id > products.size() - 1 || newPrice < 0 || newPrice > 499999) {
                    System.out.println("Неккоретный ID или цена\nМаксвимальное цена - " + 499999);
                    ConsoleUtil.sleep(1500);
                } else {
                    Product p = products.get(id);
                    System.out.printf("%-25s\t%.0f -----> %.0f\n\n", p.getName(), p.getPrice(), newPrice);
                    System.out.print("Подтверить?\n Да - 1\t Нет - 2\nВвод -> ");
                    choose = ConsoleIO.getline();
                    if ("1".equals(choose)) {
                        p.setPrice(newPrice);
                        UpdateDataBase();
                        System.out.println("Цена успешно изменена!\n");
                        ConsoleUtil.sleep(1500);
                        ConsoleUtil.clear();
                        break;
                    } else if ("2".equals(choose)) {
                        System.out.println("Отмена операции!");
                        ConsoleUtil.sleep(1500);
                    } else {
                        ConsoleIO.err();
                    }
                }
            }
        }
    }

    public void ChengeName() {
        String newName, choose, chooseId;
        int id;

        while (true) {
            ConsoleUtil.clear();
            ShowStorage(3);
            System.out.print("Введите ID товара или \"exit\" для выхода -> ");
            chooseId = ConsoleIO.getline();

            if ("exit".equals(chooseId)) {
                System.out.println("Операция смены названия прервана!\n");
                ConsoleUtil.sleep(1500);
                break;
            }

            System.out.print("Введите название нового товара -> ");
            newName = ConsoleIO.getline();

            if (newName.length() <= 0 || newName.length() >= 60 || "exit".equals(newName)) {
                System.out.println("Ошибка: Максимальная длина названия 60 символов");
                ConsoleUtil.sleep(1500);
            } else if (numbers.IsNumber(chooseId)) {
                id = Integer.parseInt(chooseId) - 1;
                if (id < 0 || id > products.size() - 1) {
                    System.out.println("Ошибка ID");
                    ConsoleUtil.sleep(1500);
                } else {
                    Product p = products.get(id);
                    System.out.printf("%-25s\t%s -----> %s\n\n", p.getName(), p.getName(), newName);
                    System.out.print("Подтверить?\n Да - 1\t Нет - 2\nВвод -> ");
                    choose = ConsoleIO.getline();
                    if ("1".equals(choose)) {
                        p.setName(newName);
                        UpdateDataBase();
                        System.out.println("Изменение названия успешно завершено");
                        ConsoleUtil.sleep(1500);
                        break;
                    } else if ("2".equals(choose)) {
                        System.out.println("Отмена");
                        ConsoleUtil.sleep(1500);
                    } else {
                        ConsoleIO.err();
                    }
                }
            }
        }
    }

    public void ChengeStorage() {
        String choose;
        while (true) {
            ConsoleUtil.clear();
            System.out.println("1 - Добавить новый товар");
            System.out.println("2 - Изменить название товара");
            System.out.println("3 - Изменить цену товара");
            System.out.println("4 - Добавить кол-во товара");
            System.out.println("5 - Уменьшить кол-во товара");
            System.out.println("6 - Удалить товар");
            System.out.println("0 - Выход из редактора склада");
            System.out.print("Ввод -> ");
            choose = ConsoleIO.getline();
            if ("1".equals(choose)) {
                AddNewItem();
            } else if ("2".equals(choose) && products.size() > 0) {
                ChengeName();
            } else if ("3".equals(choose) && products.size() > 0) {
                ChengePrice();
            } else if ("4".equals(choose) && products.size() > 0) {
                AddStorageItem();
            } else if ("5".equals(choose) && products.size() > 0) {
                RemoveStorageItem();
            } else if ("6".equals(choose) && products.size() > 0) {
                DeleteItem();
            } else if ("0".equals(choose)) {
                ConsoleUtil.clear();
                break;
            } else {
                ConsoleIO.err();
            }
        }
    }

    public void AddNewItem() {
        String newName, newPrice = "", newCount = "", choose;
        boolean exit = true;
        Product newItem = new Product();

        while (true) {
            while (true) {
                ConsoleUtil.clear();
                System.out.println("\tДобавление нового товара!\n\nВведите \"exit\" для прекращении операции\n");
                System.out.print("Введите название нового товара через пробел -> ");
                newName = ConsoleIO.getline();

                if ("exit".equals(newName)) {
                    System.out.println("Операция добавления товара прервана!\n");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }

                if (newName.length() <= 0 || newName.length() >= 60) {
                    System.out.println("Ошибка: Максимальная длина названия 60 символов");
                    ConsoleUtil.sleep(1500);
                    ConsoleUtil.clear();
                } else {
                    break;
                }
            }

            while (exit) {
                System.out.print("Введите кол-во нового товара -> ");
                newCount = ConsoleIO.getline();

                if ("exit".equals(newCount)) {
                    System.out.println("Операция добавления товара прервана!\n");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }

                if (numbers.IsNumber(newCount)) {
                    int count = Integer.parseInt(newCount);
                    if (count > maxItemSize) {
                        System.out.println("Ошибка максимального размера товара! Не более " + maxItemSize + " ед.\n");
                    } else {
                        break;
                    }
                }
            }

            while (exit) {
                System.out.print("Введите цену нового товара -> ");
                newPrice = ConsoleIO.getline();

                if ("exit".equals(newPrice)) {
                    System.out.println("Операция добавления товара прервана!\n");
                    ConsoleUtil.sleep(1500);
                    exit = false;
                    break;
                }

                if (numbers.IsNumber(newPrice)) {
                    int price = Integer.parseInt(newPrice);
                    if (price > 499999) {
                        System.out.println("Ошибка максимального размера цены товара! Не более " + 499999 + " ед.\n");
                        ConsoleUtil.sleep(1500);
                    } else {
                        break;
                    }
                }
            }

            ConsoleUtil.clear();

            if (exit) {
                System.out.println("Новый товар: " + newName + "\nКол-во: " + newCount + "\nЦена: " + newPrice + "\n");
                System.out.print("Подтвердить?\n1 - Да\t2 - Нет\nВвод -> ");
                choose = ConsoleIO.getline();

                if ("1".equals(choose)) {
                    newItem.setIDproduct(products.size() + 1);
                    newItem.setName(newName);
                    newItem.setCount(Integer.parseInt(newCount));
                    newItem.setPrice(Integer.parseInt(newPrice));
                    products.add(newItem);

                    System.out.print("Идет подготовка...");
                    ConsoleUtil.sleep(2000);
                    System.out.println("Товар успешно добавлен!\n");
                    ConsoleUtil.sleep(1500);

                    UpdateDataBase();
                } else if ("2".equals(choose)) {
                    System.out.println("Отмена\n");
                    ConsoleUtil.sleep(1500);
                } else {
                    ConsoleIO.err();
                }
            }

            if (!exit) {
                break;
            }
        }
    }

    public void DeleteItem() {
        String chooseId, choose;
        int id;

        while (true) {
            ConsoleUtil.clear();
            ShowStorage(3);
            System.out.print("\nВведите ID товара для удаление или \"exit\" для выхода -> ");
            chooseId = ConsoleIO.getline();
            if ("exit".equals(chooseId)) {
                System.out.println("Операция удаления товара прервана!\n");
                ConsoleUtil.sleep(1500);
                break;
            }

            if (numbers.IsNumber(chooseId)) {
                id = Integer.parseInt(chooseId) - 1;
                if (id < 0 || id > products.size() - 1) {
                    System.out.println("Ошибка ID");
                    ConsoleUtil.sleep(1500);
                } else {
                    System.out.println("Удаляемый товар -> " + products.get(id).getName() + "\n");
                    System.out.print("Подтвердить?\n1 - Да\t2 - Нет\nВвод -> ");
                    choose = ConsoleIO.getline();

                    if ("1".equals(choose)) {
                        products.remove(id);
                        UpdateDataBase();
                    } else if ("2".equals(choose)) {
                        System.out.println("Отмена удаления товара!");
                        ConsoleUtil.sleep(1500);
                    } else {
                        ConsoleIO.err();
                    }
                }
            }
        }
    }

    public void ShowStorage(int mode) {
        if (mode == 0) {
            System.out.println("ID\t" + String.format("%-25s", "Название товара\t\t") + "Кол-во\t" + "Цена");
            for (Product i : products) System.out.println(i);
            ConsoleUtil.pause();
            ConsoleUtil.clear();
        } else if (mode == 1) {
            System.out.println("ID\t" + String.format("%-25s", "Название товара\t\t") + "Кол-во");
            for (Product i : products) {
                System.out.printf("%d\t%-25s\t%d\n", i.getIDproduct(), i.getName(), i.getCount());
            }
            ConsoleUtil.pause();
            ConsoleUtil.clear();
        } else if (mode == 2) {
            System.out.println("ID\t" + String.format("%-25s", "Название товара\t\t") + "Цена");
            for (Product i : products) {
                System.out.printf("%d\t%-25s\t%.0f\n", i.getIDproduct(), i.getName(), i.getPrice());
            }
            ConsoleUtil.pause();
            ConsoleUtil.clear();
        } else if (mode == 3) {
            System.out.println("ID\t" + String.format("%-15s", "Название товара"));
            for (Product i : products) {
                System.out.printf("%d\t%-25s\n", i.getIDproduct(), i.getName());
            }
            ConsoleUtil.pause();
            ConsoleUtil.clear();
        }
    }

    public void UpdateDataBase() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileProducts), StandardCharsets.UTF_8))) {
            for (Product i : products) {
                bw.write(i.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("База данных не открыта!");
        }
    }

    public List<Product> ProductsVector() {
        return products;
    }

    public Product findProduct(int id) {
        for (Product i : products) {
            if (i.getIDproduct() == id) return i;
        }
        System.out.println("Нету продукта с таким ID!");
        ConsoleUtil.sleep(2000);
        ConsoleUtil.clear();
        return null;
    }

    public int getStotageSize() {
        return products.size();
    }
}

