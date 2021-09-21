/**
 * 同步算税请求body
 *
 * @author wcl
 */
public class TaxQuery {

    // 字段名称	字段含义	字段类型	必填	说明

    /**
     * 外部业务编号 是
     */
    private String bizNo;

    /**
     * 企业名称 是
     */
    private String qymc;

    /**
     * 密码类型 否	默认不填则设置为0， 0表示申报密码；2表示实名账号实名密码
     */
    private String mmlx;

    /**
     * 实名账号	String	条件必填	当mmlx=2时，必填
     */
    private String smzh;

    /**
     * 实名密码	String	条件必填	当mmlx=2时，必填
     */
    private String smmm;

    /**
     * 加密实名密码	String	否	默认不填则设置为0，0表示不加密；1表示加密  参考3.4代报、实名密码加密说明
     */
    private String jmsmmm;

    /**
     * 登记序号	String	是
     */
    private String djxhid;

    /**
     * 税号	String	是
     */
    private String nsrsbh;

    /**
     * 地区编号	String	是	6位行政区划代码，至少精确到市
     */
    private String areaid;

    /**
     * 部门编号	String	否	分部门代报时必传
     */
    private String bmbh;

    /**
     * 部门名称	String	否
     */
    private String bmmc;

    /**
     * 税款所属期	String	是	默认为当前月份，格式YYYYMM
     */
    private String skssq;

    /**
     * 申报密码	String	条件必填	当mmlx=0时，必填
     */
    private String sbmm;

    /**
     * 加密申报密码	String	否	默认不填则设置为0，0表示不加密；1表示加密
     */
    private String jmsbmm;

    /**
     * 综合所得	对象		见综合所得
     */
    private Object zhsd;

    /**
     * 分类所得	对象		见分类所得
     */
    private Object flsd;

    /**
     * 非居民所得	对象		见非居民所得
     */
    private Object fjmsd;
}
