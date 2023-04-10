package test;

/**
 * @author wo
 */
public class DataStructure {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        printN(10000);
        long l2 = System.currentTimeMillis();


        long l3 = System.currentTimeMillis();
        printN2(22150);
        long l4 = System.currentTimeMillis();
        System.out.println("循环耗时：" + (l2 - l1) + "ms");
        System.out.println("递归耗时：" + (l4 - l3) + "ms");
    }

    private static void printN(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
    }

    private static void printN2(int n) {
        if (n != 0) {
            printN2(n - 1);
            System.out.println(n);
        }
    }
}
