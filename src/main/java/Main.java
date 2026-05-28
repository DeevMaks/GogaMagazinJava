import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        ConsoleIO.init(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)));

        Shop shop = new Shop();
        shop.start();
    }
}

