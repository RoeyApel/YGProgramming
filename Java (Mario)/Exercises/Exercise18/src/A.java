public class A implements B {
    public static void aaa() {
        System.out.println("kill me");
        System.err.println(B.sum(3));
        Object b = new Object();
    }
}
