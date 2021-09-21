package tax;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 累计专项扣除额
 */
@Data
public class Ljzxkc {

    /**
     * 累计继续教育支出
     */
    private BigDecimal ljjxjyzc;

    /**
     * 累计赡养老人支出
     */
    private BigDecimal ljsylrzc;

    /**
     * 累计房屋贷款支出
     */
    private BigDecimal ljzfdklxzc;

    /**
     * 累计住房租金支出
     */
    private BigDecimal ljzfzjzc;

    /**
     * 累计子女教育支出
     */
    private BigDecimal ljznjyzc;

    /**
     * 所得项目名称
     */
    private String sdxm;

    /**
     * 证照号码
     */
    private String zzhm;

    /**
     * 证照类型
     */
    private String zzlx;

    /**
     * 姓名
     */
    private String xm;
}