package com.github.face.user.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.face.user.entity.ExportDemo;
import com.github.face.user.entity.SysUser;
import com.github.face.user.mapper.SysUserMapper;
import com.github.face.user.service.SysUserService;
import com.github.face.utils.ExcelPoiUtils;
import com.github.face.utils.ExportUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUserName, username);
        return getOne(wrapper);
    }

    @Override
    public void exportTest(HttpServletResponse response) {
        ExportDemo demo1 = new ExportDemo();
        // 固定列
        demo1.setDepartName("住建局");
        demo1.setResolutionRate("82%");
        demo1.setDealCount(204);
        demo1.setTotalEvaCount(62);
        demo1.setValidEvaCount(7);
        // 动态列
        List<ExcelPoiUtils.Demo.Type> types = new ArrayList<>();
        ExcelPoiUtils.Demo.Type type1 = new ExcelPoiUtils.Demo.Type();
        type1.setTypeName("民生满意度");
        type1.setScore("35分");
        ExcelPoiUtils.Demo.Type type2 = new ExcelPoiUtils.Demo.Type();
        type2.setTypeName("卫生治理");
        type2.setScore("46分");
        ExcelPoiUtils.Demo.Type type3 = new ExcelPoiUtils.Demo.Type();
        type3.setTypeName("公共安全");
        type3.setScore("52分");
        ExcelPoiUtils.Demo.Type type4 = new ExcelPoiUtils.Demo.Type();
        type4.setTypeName("绿化面积");
        type4.setScore("65分");
        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        demo1.setTypes(types);

        ExportDemo demo2 = new ExportDemo();
        demo2.setDepartName("民政局");
        demo2.setResolutionRate("62%");
        demo2.setDealCount(9661);
        demo2.setTotalEvaCount(560);
        demo2.setValidEvaCount(80000);

        List<ExcelPoiUtils.Demo.Type> types2 = new ArrayList<>();
        ExcelPoiUtils.Demo.Type typeA = new ExcelPoiUtils.Demo.Type();
        typeA.setTypeName("民生满意度");
        typeA.setScore("102分");
        ExcelPoiUtils.Demo.Type typeB = new ExcelPoiUtils.Demo.Type();
        typeB.setTypeName("卫生治理");
        typeB.setScore("60分");
        ExcelPoiUtils.Demo.Type typeC = new ExcelPoiUtils.Demo.Type();
        typeC.setTypeName("公共安全");
        typeC.setScore("4分");
        ExcelPoiUtils.Demo.Type typeD = new ExcelPoiUtils.Demo.Type();
        typeD.setTypeName("绿化面积");
        typeD.setScore("88分");
        types2.add(typeA);
        types2.add(typeB);
        types2.add(typeC);
        types2.add(typeD);
        demo2.setTypes(types2);

        List<ExportDemo> list = new ArrayList<>();
        list.add(demo1);
        list.add(demo2);

        // 动态标头名称字段
        final String headerName = "typeName";
        // 动态标头值字段
        final String headerValue = "score";
        try {
            ExcelPoiUtils.dynamicExport(response, System.currentTimeMillis() + "", "我是标题行！",
                    "sheet名称", list, headerName, headerValue);
        } catch (Exception e) {
            log.error("导出错误，", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importExcel(MultipartFile file, HttpServletResponse response) {
        try {
            List<ExcelDTO> list = ExcelImportUtil.importExcel(file.getInputStream(), ExcelDTO.class, new ImportParams());
            String regex = "()";
            String regex1 = "（）";
            for (ExcelDTO dto : list) {
                if (StringUtils.isBlank(dto.getQuestion())) {
                    log.info("跳过，序号{}", dto.getNum());
                    continue;
                }
                String question = dto.getQuestion().replaceAll(" ", "").replaceAll("　", "");
                if (question.contains(regex)) {
                    dto.setType("填空");
                    dto.setContent(question.replaceAll(regex, "（" + dto.getAnswer() + "）"));
                } else if (question.contains(regex1)) {
                    dto.setType("填空");
                    dto.setContent(question.replaceAll(regex1, "（" + dto.getAnswer() + "）"));
                } else {
                    dto.setType("判断");
                    dto.setContent(question + "（" + dto.getAnswer() + "）");
                }
            }
            ExportUtil.downloadExcelEntity(response, list, ExcelDTO.class, System.currentTimeMillis() + "", "试卷", null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class ExcelDTO implements Serializable {
        @Excel(name = "序号")
        private String num;
        @Excel(name = "题目")
        private String question;
        @Excel(name = "A")
        private String a;
        @Excel(name = "B")
        private String b;
        @Excel(name = "C")
        private String c;
        @Excel(name = "D")
        private String d;
        @Excel(name = "答案")
        private String answer;
        @Excel(name = "类型")
        private String type;
        @Excel(name = "打印内容")
        private String content;
    }
}
