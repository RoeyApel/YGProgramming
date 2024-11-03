import java.util.Scanner;

public class c2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            int num = getIntInput(scanner);
            String isInFib = (isInFib(num)) ? "Yes" : "NO";
            System.out.println(num + ": " + isInFib);
            scanner.nextLine();
        }
    }

    public static int getIntInput(Scanner scanner) {
        System.out.print("Number: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Number: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    public static boolean isInFib(int num) {
        int beforeN = 1, currentN = 1, newN = beforeN + currentN;

        while (newN < num) {
            beforeN = currentN;
            currentN = newN;
            newN = beforeN + currentN;
        }
        if (newN == num) {
            return true;
        }
        return false;
    }
}
