import java.util.Scanner;

public class c1 {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Number: ");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        printFib(num);
        scanner.close();
    }

    public static void printFib(int num) {
        int beforeN = 1, currentN = 1, newN = beforeN + currentN;
        System.out.print("1, 1, 2");

        while (newN <= num) {
            beforeN = currentN;
            currentN = newN;
            newN = beforeN + currentN;
            System.out.print(", " + newN);
        }

    }
}
