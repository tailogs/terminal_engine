import java.util.Scanner;

public class Engine {
    private final static Scanner sc = new Scanner(System.in);
    private final Color colorText = Color.valueOf(Color.WHITE.getCode());

    public static void printText(String text) {
        System.out.print(text);
    }

    public static void printText(String text, int amount, String endText) {
        for (int i = 0; i < amount; i++)
            System.out.print(text);
        System.out.print(endText);
    }

    public static String getInput() {
        System.out.print("> ");
        return sc.nextLine();
    }

    public static String getInput(String text) {
        System.out.print(text + " ");
        return sc.nextLine();
    }

    public void printlnText(String text) {
        System.out.println(colorText.getCode() + text + Color.RESET.getCode());
    }
}
