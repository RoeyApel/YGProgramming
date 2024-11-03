public class TableSetting {
    private int hosts;
    private Spoon[] spoons;
    private Fork[] forks;
    private Knife[] knifes;
    private DinnerPlate[] dinnerPlates;
    private WindGlass[] windGlasses;

    public TableSetting(int hosts) {
        this.hosts = hosts;

        spoons = new Spoon[hosts];
        forks = new Fork[hosts];
        knifes = new Knife[hosts];
        dinnerPlates = new DinnerPlate[hosts];
        windGlasses = new WindGlass[hosts];

        for (int i = 0; i < hosts; i++) {
            dinnerPlates[i] = new DinnerPlate();
            windGlasses[i] = new WindGlass();
            knifes[i] = new Knife();
            forks[i] = new Fork();
            spoons[i] = new Spoon();
        }
    }

    public void show(){
        for (int i = 0; i < hosts; i++) {
            System.err.println("host" + (i + 1) + ": " + " "+
            spoons[i].toString()+ " " +
            forks[i].toString()+" " +
            knifes[i].toString()+" " +
            dinnerPlates[i].toString()+ " " +
            windGlasses[i].toString());
        }
    }

}
