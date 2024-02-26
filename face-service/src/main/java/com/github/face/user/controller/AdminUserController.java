package com.github.face.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.face.user.entity.User;
import com.github.face.user.request.UserAddRequest;
import com.github.face.user.request.UserQueryRequest;
import com.github.face.user.request.UserUpdateRequest;
import com.github.face.user.service.UserService;
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
@Tag(name = "用户管理", description = "/api/admin/user")
@RequestMapping("/api/admin/user")
public class AdminUserController {

    @Resource
    private UserService userService;

    @Operation(summary = "分页查询", parameters = {@Parameter( name = "request", description = "查询参数")})
    @GetMapping("/page")
    public IPage<User> page(UserQueryRequest request, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return userService.page(new Page<>(pageNo, pageSize), request.toWrapper());
    }

    @Operation(summary = "新增", parameters = {@Parameter(name = "request", description = "新增对象")})
    @PostMapping()
    public Boolean add(@RequestBody UserAddRequest request) {
        User entity = new User();
        BeanUtils.copyProperties(request, entity);
        return userService.save(entity);
    }

    @Operation(summary = "修改", parameters = {@Parameter(name = "request", description = "修改对象")})
    @PutMapping()
    public Boolean update(@RequestBody UserUpdateRequest request) {
        User entity = new User();
        BeanUtils.copyProperties(request, entity);
        return userService.updateById(entity);
    }

    @Operation(summary = "删除", parameters = {@Parameter(name = "id", description = "id")})
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }
}