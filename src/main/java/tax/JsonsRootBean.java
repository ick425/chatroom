package tax;

import lombok.Data;

/**
 *
 */
@Data
public class JsonsRootBean {

    /**
     * 外部业务编号
     */
    private String bizNo;
    /**
     * 外部业务编号
     */
    private String djxhid;
    /**
     * 企业名称
     */
    private String qymc;
    /**
     * 税号
     */
    private String nsrsbh;
    /**
     * 地区编号
     */
    private String areaid;
    /**
     * 部门编号
     */
    private String bmbh;
    /**
     * 税款所属期,默认为当前月份，格式YYYYMM
     */
    private String skssq;
    /**
     * 申报账号
     */
    private String wsxtyhm;
    /**
     * 综合所得
     */
    private Zhsd zhsd;

}