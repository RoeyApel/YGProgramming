import java.util.Scanner;

public class c1 {

    public static void main(String[] args) {
        System.out.print("num: ");
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[] { 1, 1, 1, 1, 4, 4, 4, 6, 7, 8, 9, 9 };
        int num = scanner.nextInt();
        System.out.println(isExist(arr, num));
    }

    private static boolean isExist(int[] arr, int num) {
        int first = 0, last = arr.length - 1, mid = 0;
        while (first <= last) {
            mid = (first + last) / 2;

            if (arr[mid] > num) {
                last = mid - 1;
            } else if (arr[mid] < num) {
                first = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
