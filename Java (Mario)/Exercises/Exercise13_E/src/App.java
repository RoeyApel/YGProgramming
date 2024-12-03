import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Mathing mathing = new Mathing();

        try {
            mathing.addPos(-1, 0);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        // ===============================================

        System.out.println("Please enter a number:");

        String input = scanner.nextLine();

        try {
            int intValue = Integer.parseInt(input);
            System.out.println("The result: " + (intValue + 10));
        } catch (NumberFormatException e1) {
            try {
                double doubleValue = Double.parseDouble(input);
                System.out.println("The result: " + (doubleValue * 10));
            } catch (NumberFormatException e2) {
                System.out.println("Invalid input: " + input);
            }
        }

        // ===============================================
        double a, b, c;
        double[] root;
        try {
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();

            root = Equation.root(a, b, c);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (WrongInputException e) {
            System.err.println(e.getMessage());
        }
    }
}
