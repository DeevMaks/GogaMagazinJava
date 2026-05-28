import java.io.IOException;

public final class ConsoleUtil {
    private ConsoleUtil() {}

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clear() {
        // ћаксимально похоже на system("cls") без зависимости от cmd/ANSI.
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static void pause() {
        System.out.print("Press Enter to continue . . .");
        try {
            System.in.read();
            while (System.in.available() > 0) System.in.read();
        } catch (IOException ignored) {
        }
        System.out.println();
    }
}

