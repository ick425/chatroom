package thread;

/**
 * 模拟龟兔赛跑
 *
 * @author wcl
 */
public class Race implements Runnable {

    private static String winner;

    private final static Integer STEPS = 100;

    private final static String RABBIT = "兔子";

    private final static String TURTLE = "乌龟";

    @Override
    public void run() {
        for (int i = 1; i <= STEPS; i++) {
            String name = Thread.currentThread().getName();
            if (RABBIT.equals(name)) {
                try {
                    // 模拟兔子睡觉
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isOver(i)) {
                break;
            }
            if (RABBIT.equals(name)) {
                System.out.println(name + "------------>跑了" + i + "米");
            } else {
                System.out.println(name + "-->跑了" + i + "米");
            }
        }
    }

    /**
     * 是否结束比赛
     *
     * @param steps 当前步数
     * @return
     */
    private boolean isOver(int steps) {
        if (winner != null) {
            return true;
        }
        if (steps >= STEPS) {
            winner = Thread.currentThread().getName();
            System.out.println("-------->冠军是【" + winner + "】");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, RABBIT).start();
        new Thread(race, TURTLE).start();
    }
}
