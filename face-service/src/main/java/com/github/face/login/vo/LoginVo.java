package com.github.face.login.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wo
 */
@Data
public class LoginVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("id")
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

    /**
     * 创建人
     */
    @TableField("created")
    private String created;

    /**
     * 修改人
     */
    @TableField("updated")
    private String updated;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除；1：删除
     */
    @TableField("is_deleted")
    private Boolean deleted;


}
