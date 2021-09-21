import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Administrator
 */
public class CollectionTest {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.addFirst("000");
        linkedList.add("555");
        linkedList.addLast("777");
        System.out.println(linkedList);
        System.out.println(linkedList.lastIndexOf("777"));
        System.out.println(linkedList);
    }
}
