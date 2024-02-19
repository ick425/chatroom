package org.example.test;

/**
 * switch必须先判空
 *
 * @author wcl
 */
public class SwitchTest {
    public static void main(String[] args) {
        Integer approveStatus = 1;
        switch (approveStatus) {
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            case 4:
                System.out.println(4);
                break;
            default:
                System.out.println("default");
        }
    }
}
