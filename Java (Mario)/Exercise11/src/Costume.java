public class Costume {
    private String type;

    public Costume(String type) {
        this.type = type;
        System.out.println(type + " constructor");
    }

    @Override
    public String toString(){
        return type;
    }
}
