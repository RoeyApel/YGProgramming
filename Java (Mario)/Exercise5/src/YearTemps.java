import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class YearTemps {

    private static String[] months = new String[] {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    private double[] temps;
    private double avegrageTemps;
    private int year;

    public YearTemps(int year) {
        this.year = year;

        int sum = 0;
        temps = new double[12];
        for (int i = 0; i < temps.length; i++) {
            temps[i] = Math.random() * 30 + 10;
            sum += temps[i];
        }

        avegrageTemps = (float) sum / temps.length;
    }

    public double GetAverage() {
        return avegrageTemps;
    }

    public void print() {
        System.out.println("Year:" + year);
        for (int i = 0; i < months.length; i++) {
            System.out.printf("Name: %s, Temp: %.2f\n", months[i], temps[i]);
        }
        System.out.printf("Average: %.2f", avegrageTemps);
    }
}
