/**
 * 水仙花数
 */
public class DaffodilsTest {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        for (int i = 100; i < 1000; i++) {
            int hundred = i / 100;
            int ten = i / 10 % 10;
            int digits = i % 10;


            int i4 = hundred * hundred * hundred
                    + ten * ten * ten
                    + digits * digits * digits;
            if (i4 == i) {
                System.out.println(i);
            }
        }
    }

}
