public class ClassTTest {
    public static void main(String[] args) {
        Tss t = new Tss("初始值");
        System.out.println(t.getName());
        String s = get(t);
        System.out.println(s);
        System.out.println(t.getName());

    }

    private static String get(Tss t){
        t.setName("改变了");
        return "get方法";
    }


    static class Tss{

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        Tss(String name) {
            this.name = name;
        }

    }

}
