public class Car {
    private double velocity;
    private double maxVelocity;

    public Car(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public void drive(double v) throws NegativeVelocityException, TooLargevelocityException {
        if (v < 0) {
            throw new NegativeVelocityException();
        }
        if (v > maxVelocity) {
            throw new TooLargevelocityException();
        }
        this.velocity = v;
    }
}
