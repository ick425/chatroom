package tax;

import lombok.Data;

import java.util.List;

/**
 * 正常工资薪金对象
 */
@Data
public class Wqxzcgzxj {

    /**
     * 正常工资薪金列表
     */
    private List<Zcgzxj> zcgzxj;

    /**
     * 往期历史申报数据列表
     */
    private List<Wqlssbsj> wqlssbsjlb;

    /**
     * 累计专项扣除额列表
     */
    private List<Ljzxkc> ljzxkc;

    /**
     * 专项附加扣除标记
     */
    private String zxkcbz;
}