package ${cfg.routerPackage}.request;

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou")>
    <#else>
import ${pkg};
    </#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ${package.Entity}.${entity};
import org.springframework.util.StringUtils;


/**
 * ${table.comment!} 查询条件
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="${entity}对象", description="${table.comment!}")
public class ${entity}QueryRequest implements Serializable {

<#list table.fields as field>
    <#if field.propertyName == "createTime">
    <#elseif field.propertyName == "deleted">
    <#elseif field.propertyName == "updateTime">
    <#elseif field.propertyType == "BigDecimal">
    <#else>
    /**
    * ${field.comment!}
    */
    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime start;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime end;

    public LambdaQueryWrapper<${entity}> toWrapper() {
        return new LambdaQueryWrapper<${entity}>()
        <#list table.fields as field>
            <#if field.propertyName == "createTime">
            <#elseif field.propertyName == "deleted">
            <#elseif field.propertyName == "updateTime">
            <#elseif field.propertyType == "BigDecimal">
            <#elseif field.propertyType == "String">
                .eq(StringUtils.hasText(${field.propertyName}), ${entity}::get${field.propertyName?cap_first}, ${field.propertyName})
            <#else>
                .eq(${field.propertyName} != null, ${entity}::get${field.propertyName?cap_first}, ${field.propertyName})
            </#if>
        </#list>
                .ge(start != null, ${entity}::getCreateTime, start)
                .lt(end != null, ${entity}::getCreateTime, end)
                .orderByDesc(${entity}::getCreateTime);
    }
}