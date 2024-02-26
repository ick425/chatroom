package com.github.face.user.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.face.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户管理 查询条件
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "User对象", description="用户管理")
public class UserQueryRequest implements Serializable {

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

    @Schema(name = "start", description = "开始时间")
    private LocalDateTime start;
    @Schema(name = "end", description = "结束时间")
    private LocalDateTime end;

    public LambdaQueryWrapper<User> toWrapper() {
        return new LambdaQueryWrapper<User>()
                .eq(id != null, User::getId, id)
                .eq(StringUtils.hasText(name), User::getName, name)
                .eq(StringUtils.hasText(pwd), User::getPwd, pwd)
                .eq(StringUtils.hasText(created), User::getCreated, created)
                .eq(StringUtils.hasText(updated), User::getUpdated, updated)
                .ge(start != null, User::getCreateTime, start)
                .lt(end != null, User::getCreateTime, end)
                .orderByDesc(User::getCreateTime);
    }
}