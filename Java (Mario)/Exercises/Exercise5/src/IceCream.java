import java.util.Arrays;

public class IceCream {
    private static String[] flavorsC = new String[] {
            "Sweet", "Sour", "Bitter", "Salty", "Umami", "Spicy", "Savory", "Tangy"
    };
    private String[] flavors;

    public IceCream(int n) {
        flavors = new String[n];

        for (int i = 0; i < flavors.length; i++) {
            int rndIndex = (int) (Math.random() * n);
            flavors[i] = flavorsC[rndIndex];
        }
        // System.out.println(Arrays.toString(flavors));
    }

    public void show() {
        System.out.println(Arrays.toString(flavors));
    }

}
