package tax;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 全年一次性奖金收入列表
 */
@Data
public class Qnycxjjss {

    /**
     * 姓名
     */
    private String xm;

    /**
     * 证件类型
     */
    private String zzlx;

    /**
     * 证件号码
     */
    private String zzhm;

    /**
     * 任职受雇日期
     */
    private String rzsgrq;

    /**
     * 当期收入额
     */
    private BigDecimal sre;

    /**
     * 当期免税收入
     */
    private BigDecimal mssd;

    /**
     * 商业健康保险
     */
    private BigDecimal syjkbx;

    /**
     * 税延养老保险
     */
    private BigDecimal syylbx;

    /**
     * 其他
     */
    private BigDecimal qt;

    /**
     * 准予扣除的捐赠额
     */
    private BigDecimal zykcjze;

    /**
     *
     */
    private BigDecimal jmse;

    /**
     * 减免税额
     */
    private String bz;

    /**
     * 其他扣除合计
     */
    private BigDecimal qtckhj;

    /**
     * 所得项目名称
     */
    private String sdxm;


}