package ${cfg.routerPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.routerPackage}.request.${entity}AddRequest;
import ${cfg.routerPackage}.request.${entity}QueryRequest;
import ${cfg.routerPackage}.request.${entity}UpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
* ${table.comment!} controller
*
* @author ${author}
* @since ${date}
*/
@Valid
@RestController
@Tag(name = "${table.comment!}", description = "/api/admin/${entity?uncap_first}")
@RequestMapping("/api/admin/${entity?uncap_first}")
public class Admin${table.controllerName} {

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @Operation(summary = "分页查询", parameters = {@Parameter( name = "request", description = "查询参数")})
    @GetMapping("/page")
    public IPage<${entity}> page(${entity}QueryRequest request, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ${table.serviceName?uncap_first}.page(new Page<>(pageNo, pageSize), request.toWrapper());
    }

    @Operation(summary = "新增", parameters = {@Parameter(name = "request", description = "新增对象")})
    @PostMapping()
    public Boolean add(@RequestBody ${entity}AddRequest request) {
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(request, entity);
        return ${table.serviceName?uncap_first}.save(entity);
    }

    @Operation(summary = "修改", parameters = {@Parameter(name = "request", description = "修改对象")})
    @PutMapping()
    public Boolean update(@RequestBody ${entity}UpdateRequest request) {
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(request, entity);
        return ${table.serviceName?uncap_first}.updateById(entity);
    }

    @Operation(summary = "删除", parameters = {@Parameter(name = "id", description = "id")})
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return ${table.serviceName?uncap_first}.removeById(id);
    }
}