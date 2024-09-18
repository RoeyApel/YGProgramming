package T3;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (!setDay(day) || !setMonth(month) || !setYear(year)) {
            day = 0;
            month = 0;
            year = 0;
            System.out.println("setting error");
        }
    }

    public Date() {
        this(1, 1, 2000);
    }

    public void print() {
        System.out.println(day + "/" + month + "/" + year);
    }

    public int getDay() {
        return day;
    }

    public boolean setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
            return true;
        }
        return false;
    }

    public int getMonth() {
        return month;
    }

    public boolean setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
            return true;
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(int year) {
        if (year >= 1900 && year <= 2100) {
            this.year = year;
            return true;
        }
        return false;
    }

}
