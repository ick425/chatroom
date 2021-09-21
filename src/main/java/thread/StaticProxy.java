package thread;

/**
 * 静态代理
 *
 * @author wcl
 */
public class StaticProxy {


    public static void main(String[] args) {
        You you = new You();
        new WeddingCompany(you).happyMarry();


        new Thread(() -> System.out.println("哈哈哈")).start();
    }


    /**
     * 结婚
     */
    interface Marry {
        /**
         * 结婚方法
         */
        void happyMarry();
    }

    /**
     * 真实角色
     */
    static class You implements Marry {

        @Override
        public void happyMarry() {
            System.out.println("张三结婚了");
        }
    }

    /**
     * 代理角色
     */
    static class WeddingCompany implements Marry {

        private Marry target;

        public WeddingCompany(Marry target) {
            this.target = target;
        }

        @Override
        public void happyMarry() {
            before();
            target.happyMarry();
            after();
        }


        void before() {
            System.out.println("结婚前，布置现场");
        }

        void after() {
            System.out.println("结婚后，收钱");
        }
    }


}
