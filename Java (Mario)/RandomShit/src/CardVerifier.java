import java.math.BigDecimal;
import java.util.Scanner;

public class CardVerifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("num: ");
        String num = scanner.nextLine();
        BigDecimal cardNumber = new BigDecimal(num);
        System.out.println(isCardNumberValid(cardNumber));
        scanner.close();
    }

    public static boolean isCardNumberValid(BigDecimal cardNumber) {
        BigDecimal tempCardNumber = cardNumber;
        int sum = 0;
        int sumOddDigits = 0;
        int sumEvenUUY = 0;
        int halfSumDigits = 0;
        int digit;

        while (tempCardNumber.compareTo(BigDecimal.ZERO) > 0) {
            sumOddDigits += tempCardNumber.remainder(BigDecimal.TEN).intValue();
            tempCardNumber = tempCardNumber.divide(new BigDecimal("100"), BigDecimal.ROUND_DOWN);
            halfSumDigits++;
        }

        tempCardNumber = cardNumber.divide(BigDecimal.TEN);

        while (tempCardNumber.compareTo(BigDecimal.ZERO) > 0) {
            digit = tempCardNumber.remainder(BigDecimal.TEN).intValue();
            digit *= 2;
            sumEvenUUY += (digit > 9) ? digit - 9 : digit;
            tempCardNumber = tempCardNumber.divide(new BigDecimal("100"), BigDecimal.ROUND_DOWN);
        }

        if (halfSumDigits * 2 != 16) {
            return false;
        }
        sum = sumEvenUUY + sumOddDigits;
        return sum % 10 == 0;
    }
}
