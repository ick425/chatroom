public class ForTest {
    public static void main(String[] args) {
        long l = test1();
        long l1 = test2();
        System.out.println("test1耗时:" + l + "ms");
        System.out.println("test2耗时:" + l1 + "ms");
    }

    public static long test1() {

        long start = System.currentTimeMillis();
        int c = 0;
        for (int i = 0; i <= 20000; i++) {
            for (int j = 0; j <= 500; j++) {
                c = i + j;
                System.out.println(c);
            }
        }
        return System.currentTimeMillis() - start;
    }

    public static long test2() {

        long start = System.currentTimeMillis();
        int c = 0;
        for (int i = 0; i <= 500; i++) {
            for (int j = 0; j <= 20000; j++) {
                c = i + j;
                System.out.println(c);
            }
        }
        return System.currentTimeMillis() - start;
    }
}
