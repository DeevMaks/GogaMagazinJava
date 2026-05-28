import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws Exception {
        // Аналог SetConsoleCP(1251)/SetConsoleOutputCP(1251) для учебного совпадения вывода.
        Charset cs = Charset.forName("windows-1251");
        System.setOut(new PrintStream(System.out, true, cs));
        System.setErr(new PrintStream(System.err, true, cs));
        ConsoleIO.init(new BufferedReader(new InputStreamReader(System.in, cs)));

        Shop shop = new Shop();
        shop.start();
    }
}

