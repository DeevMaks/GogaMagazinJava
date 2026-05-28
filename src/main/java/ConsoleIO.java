import java.io.BufferedReader;
import java.io.IOException;

public final class ConsoleIO {
    private static BufferedReader in;

    private ConsoleIO() {}

    public static void init(BufferedReader reader) {
        in = reader;
    }

    public static void getline(StringRef ref) {
        try {
            ref.value = in.readLine();
            if (ref.value == null) ref.value = "";
        } catch (IOException e) {
            ref.value = "";
        }
    }

    public static String getline() {
        StringRef r = new StringRef();
        getline(r);
        return r.value;
    }

    public static void err() {
        System.out.println("Неккоретный ввод!");
        ConsoleUtil.sleep(1500);
        ConsoleUtil.clear();
    }
}

