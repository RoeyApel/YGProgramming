public class c3 {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 34, 3443, 4334, 5656, 334, 120, 99, 34, 22, 11 };
        System.out.println(getBiggestNum(arr));
    }

    private static int getBiggestNum(int[] arr) {
        int first = 0, last = arr.length - 1, mid = 0;

        while (first <= last) {
            mid = (first + last) / 2;

            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                first = mid + 1;
            } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                last = mid - 1;
            }
        }
        return -1;
    }
}
