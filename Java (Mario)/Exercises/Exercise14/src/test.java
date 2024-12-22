public class test {

}
// Java program to demonstrate that non-method
// members are accessed according to reference
// type (Unlike methods which are accessed according
// to the referred object)

class Parent {
    int value = 1000;

    public String toString() {
        return "ggrrr";
    }
}

class Child extends Parent {
    int value = 10;

    @Override
    public String toString() {
        return "gg";
    }

    class Child2 extends Parent {
        int value = 10;

        @Override
        public String toString() {
            return "ggrrrr";
        }
    }

    // Driver class
    class Test {
        public static void main(String[] args) {
            Parent cobj = new Child2();
            Parent par = cobj;
            System.out.println(cobj.toString());

            // Using instanceof to make sure that par
            // is a valid reference before typecasting
            if (true) {
                System.out.println(
                        "Value accessed through "
                                + "parent reference with typecasting is "
                                + par.toString());
            }
        }
    }
}