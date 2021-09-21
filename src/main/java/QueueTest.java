import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {

        Queue<Integer> integerQueue = new LinkedList<>();
        integerQueue.add(10);
        integerQueue.peek();
        integerQueue.poll();
    }
}
