package com.github.face.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.face.user.entity.SysUser;
import com.github.face.user.request.SysUserAddRequest;
import com.github.face.user.request.SysUserQueryRequest;
import com.github.face.user.request.SysUserUpdateRequest;
import com.github.face.user.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户管理 controller
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Valid
@RestController
@Tag(name = "用户管理", description = "/api/admin/sysUser")
@RequestMapping("/api/admin/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Operation(summary = "分页查询", parameters = {@Parameter(name = "request", description = "查询参数")})
    @GetMapping("/page")
    public IPage<SysUser> page(SysUserQueryRequest request, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return sysUserService.page(new Page<>(pageNo, pageSize), request.toWrapper());
    }

    @Operation(summary = "新增", parameters = {@Parameter(name = "request", description = "新增对象")})
    @PostMapping()
    public Boolean add(@RequestBody SysUserAddRequest request) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(request, entity);
        return sysUserService.save(entity);
    }

    @Operation(summary = "修改", parameters = {@Parameter(name = "request", description = "修改对象")})
    @PutMapping()
    public Boolean update(@RequestBody SysUserUpdateRequest request) {
        SysUser entity = new SysUser();
        BeanUtils.copyProperties(request, entity);
        return sysUserService.updateById(entity);
    }

    @Operation(summary = "删除", parameters = {@Parameter(name = "id", description = "id")})
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return sysUserService.removeById(id);
    }
}