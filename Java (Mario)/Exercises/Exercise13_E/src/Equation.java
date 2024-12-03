public class Equation {
    public static double[] root(double a, double b, double c) throws WrongInputException {
        double d1, d2;

        if (a == 0) {
            throw new WrongInputException("a can't be zero");
        }
        double sq = Math.sqrt(b * b - 4 * a * c);
        d1 = (-b + sq) / 2 * a;
        d2 = (-b - sq) / 2 * a;
        if (d1 < 0) {
            throw new WrongInputException("zero < Discriminant");
        }
        return new double[] { d1, d2 };
    }
}
