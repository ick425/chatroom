package com.github.face.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户管理 更新request
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "SysUser对象", description="用户管理")
public class SysUserUpdateRequest implements Serializable {

    /**
     * 用户id
     */
    @Schema(name = "id", description = "用户id")
    private Long id;

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @Schema(name = "phone", description = "手机号")
    private String phone;

    /**
     * 密码
     */
    @Schema(name = "pwd", description = "密码")
    private String pwd;

    /**
     * 盐
     */
    @Schema(name = "salt", description = "盐")
    private String salt;

    /**
     * 部门id
     */
    @Schema(name = "departId", description = "部门id")
    private Long departId;

    /**
     * 是否启用; 1:启用 0:禁用
     */
    @Schema(name = "status", description = "是否启用; 1:启用 0:禁用")
    private Boolean status;

    /**
     * 创建人
     */
    @Schema(name = "created", description = "创建人")
    private String created;

    /**
     * 修改人
     */
    @Schema(name = "updated", description = "修改人")
    private String updated;

}