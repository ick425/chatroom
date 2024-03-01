package com.github.face.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.face.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author wangcl
 * @since 2024-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 密码
     */
    @TableField("pwd")
    private String pwd;

    /**
     * 盐
     */
    @TableField("salt")
    private String salt;

    /**
     * 部门id
     */
    @TableField("depart_id")
    private Long departId;

    /**
     * 是否启用; 1:启用 0:禁用
     */
    @TableField("status")
    private Boolean status;


}
