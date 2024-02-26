package ${cfg.routerPackage}.request;

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou")>
    <#else>
import ${pkg};
    </#if>
</#list>
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ${table.comment!} 更新request
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "${entity}对象", description="${table.comment!}")
public class ${entity}UpdateRequest implements Serializable {

<#list table.fields as field>
    <#if field.propertyName == "deleted">
    <#elseif field.propertyName == "createTime">
    <#elseif field.propertyName == "updateTime">
    <#else>
    /**
     * ${field.comment!}
     */
    @Schema(name = "${field.propertyName}", description = "${field.comment}")
    private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>
}