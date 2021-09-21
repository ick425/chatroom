package tax;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 正常工资薪金列表
 */
@Data
public class Zcgzxj {

    /**
     * 任职受雇日期,格式YYYY-MM-DD
     */
    private String rzsgrq;

    /**
     * 基本养老保险
     */
    private BigDecimal jbylaobxf;

    /**
     * 基本医疗保险
     */
    private BigDecimal jbylbxf;

    /**
     * 失业保险
     */
    private BigDecimal sybxf;

    /**
     * 住房公积金
     */
    private BigDecimal zfgjj;

    /**
     * 年金
     */
    private BigDecimal nj;

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
     * 当期免税收入
     */
    private BigDecimal mssd;

    /**
     * 当期收入额
     */
    private BigDecimal sre;

    /**
     * 所得项目名称
     */
    private String sdxm;

    /**
     * 证件号码
     */
    private String zzhm;

    /**
     * 证件类型
     */
    private String zzlx;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 备注
     */
    private String bz;

    /**
     * 减免税额
     */
    private int jmse;

}