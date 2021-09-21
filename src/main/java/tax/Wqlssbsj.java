package tax;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 往期历史申报数据
 */
@Data
public class Wqlssbsj {

    /**
     * 累计减免税额
     */
    private BigDecimal ljjmse;

    /**
     * 累计免税收入
     */
    private BigDecimal ljmssr;

    /**
     * 累计其他扣除合计
     */
    private BigDecimal ljqtkchj;

    /**
     * 累计收入额
     */
    private BigDecimal ljsre;

    /**
     * 累计已预缴税额
     */
    private Double ljyiyujse;

    /**
     * 累计专项扣除合计
     */
    private BigDecimal ljzxkchj;

    /**
     * 累计准予扣除的捐赠额
     */
    private BigDecimal ljzykcdjze;

    /**
     * 所得项目类型
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

    /**
     * 国籍
     */
    private String gj;


}