/**
 * 接口测试类
 *
 * @author wcl
 */
public @interface AnnotationTest {
    int age();

    String name() default "傻子";
}


