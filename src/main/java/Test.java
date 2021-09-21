import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Test {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] strings = new String[list.size()];
        List<String> list1 = Arrays.asList(strings);
        list1.set(0, "aaaa");
        list1.set(2, "cccc");

        System.out.println(list1);

    }
}
