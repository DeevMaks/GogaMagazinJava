import java.text.DecimalFormat;

public class Product {
    private String name = "";
    private int count = 0;
    private int id = 0;
    private double price = 0.0;

    public Product() {}

    public Product(String name, int count, int id, double price) {
        this.name = name;
        this.count = count;
        this.id = id;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setIDproduct(int id) {
        this.id = id;
    }

    public int getIDproduct() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String ToString() {
        // Аналог ostringstream + setw/left. Делаем похожее табами/шириной.
        String leftName = String.format("%-20s", name);
        return id + "\t" + leftName + "\t\t" + count + "\t" + formatPrice(price);
    }

    private static String formatPrice(double v) {
        DecimalFormat df = new DecimalFormat("0.########");
        return df.format(v);
    }

    @Override
    public String toString() {
        return ToString();
    }
}

