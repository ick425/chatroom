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
@Tag(name = "User对象", description="用户管理")
public class UserUpdateRequest implements Serializable {

    /**
     * 
     */
    @Schema(name = "id", description = "")
    private Integer id;

    /**
     * 用户名
     */
    @Schema(name = "name", description = "用户名")
    private String name;

    /**
     * 密码
     */
    @Schema(name = "pwd", description = "密码")
    private String pwd;

    /**
     * 
     */
    @Schema(name = "created", description = "")
    private String created;

    /**
     * 
     */
    @Schema(name = "updated", description = "")
    private String updated;

}