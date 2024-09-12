package T1;
public class CrazyClass {
    private String firstString;
    private String secondString;

    public CrazyClass() {
        firstString = "";
        secondString = "";
    }

    public CrazyClass(String firstString, String secondString) {
        this.firstString = firstString;
        this.secondString = secondString;
    }
    public boolean equals(CrazyClass cc){
        return firstString.equals(cc.firstString) && secondString.equals(cc.secondString);
    }
    public void show(){
        System.out.println(firstString + secondString);
    }

    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }
    
}
