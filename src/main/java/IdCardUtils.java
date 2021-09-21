import org.apache.commons.lang.StringUtils;

/**
 * @author wcl
 */
public class IdCardUtils {

    /**
     * 正则表达式：验证港澳居民通行证 H/M + 10位或8位数字
     */
    public static final String REGEX_HK_CARD = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";

    /**
     * 正则表达式：验证台湾居民通行证 新版8位或18位数字,旧版10位数字 + 英文字母
     */
    public static final String REGEX_TW_CARD = "^\\d{8}|^[a-zA-Z0-9]{10}|^\\d{18}$";

    /**
     * 校验港澳通行证
     *
     * @param HMNo 港澳通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isHMCard(String HMNo) {
        //校验非空
        if (StringUtils.isBlank(HMNo)) {
            return false;
        }
        return HMNo.matches(REGEX_HK_CARD);
    }

    /**
     * 校验台湾通行证
     * 规则 新版8位或18位数字,旧版10位数字 + 英文字母
     *
     * @param TWNo 台湾通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isTWCard(String TWNo) {
        //校验非空
        if (StringUtils.isBlank(TWNo)) {
            return false;
        }
        return TWNo.matches(REGEX_TW_CARD);
    }

    public static void main(String[] args) {
        System.out.println(isHMCard("H12345067890"));
        System.out.println(isTWCard("12345678"));

    }
}
