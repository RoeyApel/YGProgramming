import java.util.Scanner;

public class c7 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int num = getIntInput(scanner);

        System.out.println(isSymmetrical(num));
    }

    public static int getIntInput(Scanner scanner) {
        System.out.print("Number: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Number: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
    private static boolean isSymmetrical(int num) {
        int sumDigits = countDigits(num);

        int last = 0, first = 0;
        while (sumDigits > 1) {
            first = num / (int) Math.pow(10, sumDigits - 1);
            last = num % 10;

            if (first != last) {
                return false;
            }
            
            num = num % (int) Math.pow(10, sumDigits - 1);
            num /= 10;
            sumDigits -= 2;
        }
        return true;
    }

    private static int countDigits(int num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }
}
