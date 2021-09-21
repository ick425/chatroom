/**
 * 九九乘法表
 */
public class NineNineTest {
    /**
     * 每个乘法的长度
     */
    private final static int MAX_LENGTH = 8;

    public static void main(String[] args) {
        test();
    }

    public static void test() {

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (j > i) {
                    continue;
                }
                // 展示优化
                StringBuilder s = new StringBuilder(j + "x" + i + "=" + i * j);
                int length = s.length();
                if (length < MAX_LENGTH) {
                    for (int k = 0; k < MAX_LENGTH - length; k++) {
                        s.append(" ");
                    }
                }
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
