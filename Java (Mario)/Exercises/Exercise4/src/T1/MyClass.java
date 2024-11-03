package T1;
public class MyClass {

    private int fieldX;
    private int fieldY;

    public MyClass() {
    }

    public MyClass(int fieldX, int fieldY) {
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public void show() {
        System.out.printf("FieldX: %d, FieldY: %d", fieldX, fieldY);
    }

    public int sum() {
        return fieldX + fieldY;
    }

    public int mult() {
        return fieldX * fieldY;
    }

    public int powerSum() {
        return fieldX * fieldX + fieldY * fieldY;
    }

    public boolean equals(MyClass otherClass){
        return fieldX == otherClass.fieldX && fieldY == otherClass.fieldY;
    }

    public int getFieldX() {
        return fieldX;
    }

    public void setFieldX(int fieldX) {
        this.fieldX = fieldX;
    }

    public int getFieldY() {
        return fieldY;
    }

    public void setFieldY(int fieldY) {
        this.fieldY = fieldY;
    }

    

}
