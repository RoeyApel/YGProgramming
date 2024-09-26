import java.util.Scanner;

public class Game {
    Board board;
    int k;

    public Game() {
        Scanner scanner = new Scanner(System.in);

        scanner.close();
    }

    public int getKFromUser(Scanner scanner) {
        System.out.print("Type number k to play Kinarow: ");
        // while (!scanner.hasNextInt()) {
        // System.out.println("Error please type a number!");
        // scanner.nextLine();
        // }
        int input = scanner.nextInt();
        return input;
    }
}
