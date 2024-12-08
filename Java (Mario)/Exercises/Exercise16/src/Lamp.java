public class Lamp {
    private int lamp;

    public Lamp() {
        lamp = 0;
    }

    public synchronized void turnOnAt(int i) {
        int mask = 1 << i;
        lamp |= mask;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Integer.toBinaryString(lamp));
    }

    public int getLamp() {
        return lamp;
    }

    public void setLamp(int lamp) {
        this.lamp = lamp;
    }

}
