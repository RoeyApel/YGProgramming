import java.util.Scanner;

public class c0 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number >> ");
        while (!scanner.hasNextInt()) {
            System.out.print("Number >> ");
            scanner.nextLine();
        }
        int x = scanner.nextInt();

        printTriangle(x);

        scanner.close();
    }

    public static void printTriangle(int x) {
        String space = "";
        for (int i = 0; i < x; i++) {
            space = space + " ";
        }
        String line = " * ";
        for (int i = 0; i < x; i++) {
            System.out.println(space + line);
            space = space.replaceFirst(" ", "");
            line = line + " * * ";
        }
    }

}
