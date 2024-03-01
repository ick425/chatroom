package com.github.face.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @author wo
 */
@Data
public class BaseEntity implements Serializable {

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
    private Integer deleted;
}
