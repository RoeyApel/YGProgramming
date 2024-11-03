import java.util.Scanner;

public class c2 {
    public static void main(String[] args) {
        System.out.print("num: ");
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[] { 1, 1, 1, 1, 4, 4, 4, 6, 7, 8, 9, 9 };
        int num = scanner.nextInt();
        scanner.close();
        System.out.println(getOcurencesCount(arr, num));
    }

    private static int getOcurencesCount(int[] arr, int num) {
        if (getFirstOcurrence(arr, num) == -1) {
            return 0;
        }
        return getLastOcurrence(arr, num) - getFirstOcurrence(arr, num) + 1;
    }

    private static int getFirstOcurrence(int[] arr, int num) {
        int first = 0, last = arr.length - 1, mid = 0;

        if (arr[first] == num) {
            return first;
        }

        while (first <= last) {
            mid = (first + last) / 2;

            if (arr[mid] > num) {
                last = mid - 1;
            } else if (arr[mid] < num) {
                first = mid + 1;
            } else {
                if (arr[mid - 1] != num) {
                    return mid;
                } else {
                    last = mid - 1;
                }
            }
        }
        return -1;
    }

    private static int getLastOcurrence(int[] arr, int num) {
        int first = 0, last = arr.length - 1, mid = 0;

        if (arr[last] == num) {
            return last;
        }

        while (first <= last) {
            mid = (first + last) / 2;

            if (arr[mid] > num) {
                last = mid - 1;
            } else if (arr[mid] < num) {
                first = mid + 1;
            } else {
                if (arr[mid + 1] != num) {
                    return mid;
                } else {
                    first = mid + 1;
                }
            }
        }
        return -1;
    }

}
