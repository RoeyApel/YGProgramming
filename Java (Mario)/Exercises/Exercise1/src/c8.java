import java.util.Scanner;

public class c8 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int num = getIntInput(scanner);

        System.out.println(isPerfectNum(num));
    }
    

    private static boolean isPerfectNum(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return (sum == num);
    }

    public static int getIntInput(Scanner scanner) {
        System.out.print("Number: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Number: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}
