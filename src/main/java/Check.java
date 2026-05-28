import java.util.ArrayList;
import java.util.List;

public class Check {
    private final List<Product> items = new ArrayList<>();

    public Check() {}

    public void addItem(Product product) {
        for (Product item : items) {
            if (item.getIDproduct() == product.getIDproduct()) {
                item.setCount(item.getCount() + product.getCount());
                return;
            }
        }
        items.add(product);
    }

    public int print() {
        double total = 0.0;

        System.out.println("-------------------- ЧЕК --------------------");
        System.out.println("Название     Кол-во   Цена     Сумма");
        System.out.println("---------------------------------------------");

        for (Product item : items) {
            double sum = item.getPrice() * item.getCount();
            total += sum;
            System.out.println(item.getName() + "\t" + item.getCount() + "\t" + (int) item.getPrice() + "\t" + (int) sum);
        }

        System.out.println("---------------------------------------------");
        System.out.println("Итого: " + (int) total);
        return (int) total;
    }

    public void clear() {
        items.clear();
    }

    public int getSize() {
        return items.size();
    }

    public List<Product> VectorItems() {
        return items;
    }
}

