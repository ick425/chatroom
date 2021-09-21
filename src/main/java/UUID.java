public class UUID {
    public static void main(String[] args) {
        System.out.println();
        for (int i = 0; i < 17; i++) {
            String s = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
            System.out.println(i + 1 + "：" + s);
            System.out.println();
        }
    }
}