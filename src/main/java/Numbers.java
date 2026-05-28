import java.util.HashSet;
import java.util.Set;

public class Numbers {
    private final Set<Character> spesialSymbols = new HashSet<>();
    private final Set<Character> passSymbols = new HashSet<>();

    public Numbers() {
        SetSpesialSymbols();
        SetPassSymbols();
    }

    public boolean IsNumber(String str) {
        if (str == null) str = "";
        if (str.length() <= 0 || str.length() >= 10) {
            System.out.println("Неккорекный ввод");
            System.out.println("Ошибка размера числа: от 1 до 9 символов включительно\n");
            ConsoleUtil.sleep(1500);
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                System.out.println("Неккорекный ввод");
                System.out.println("Ошибка размера числа: введенные данные не являются числом\n");
                ConsoleUtil.sleep(1500);
                return false;
            }
        }
        return true;
    }

    public void SetSpesialSymbols() {
        for (char i = '0'; i <= '9'; i++) spesialSymbols.add(i);
        for (char i = 'A'; i <= 'Z'; i++) spesialSymbols.add(i);
        for (char i = 'a'; i <= 'z'; i++) spesialSymbols.add(i);
    }

    public void SetPassSymbols() {
        for (char i = '!'; i <= '&'; i++) passSymbols.add(i);
        for (char i = '('; i <= '+'; i++) passSymbols.add(i);
        for (char i = '.'; i <= '~'; i++) passSymbols.add(i);
    }

    public Set<Character> getSpesialSymbols() {
        return spesialSymbols;
    }

    public Set<Character> getPassSymbols() {
        return passSymbols;
    }
}

