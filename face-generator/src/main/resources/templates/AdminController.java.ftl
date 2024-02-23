package ${cfg.routerPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${cfg.routerPackage}.request.${entity}AddRequest;
import ${cfg.routerPackage}.request.${entity}QueryRequest;
import ${cfg.routerPackage}.request.${entity}UpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
<#--@ApiOperation("${table.comment!}")-->
@RestController
@Api(value = "/api/admin/${entity?uncap_first}", tags = "${table.comment!}")
@RequestMapping("/api/admin/${entity?uncap_first}")
public class Admin${table.controllerName} {

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public IPage<${entity}> page(${entity}QueryRequest request, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return ${table.serviceName?uncap_first}.page(new Page<>(pageNo, pageSize), request.toWrapper());
    }

    // @PostMapping("/add")
    public Boolean add(@RequestBody ${entity}AddRequest request) {
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(request, entity);
        return ${table.serviceName?uncap_first}.save(entity);
    }

    // @PutMapping("/edit")
    public Boolean update(@RequestBody ${entity}UpdateRequest request) {
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(request, entity);
        return ${table.serviceName?uncap_first}.updateById(entity);
    }

    // @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return ${table.serviceName?uncap_first}.removeById(id);
    }
}