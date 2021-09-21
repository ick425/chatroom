package tax;

import lombok.Data;

import java.util.List;

/**
 * 综合所得
 */
@Data
public class Zhsd {

    /**
     * 全年一次性奖金收入列表列表
     */
    private List<Qnycxjjss> qnycxjjsslb;

    /**
     * 稿酬所得列表
     */
    private List<Gcsd> gcsdlb;

    /**
     * 劳务报酬列表
     */
    private List<Lwbc> lwbclb;

    /**
     * 正常工资薪金对象
     */
    private Wqxzcgzxj wqxzcgzxj;

}