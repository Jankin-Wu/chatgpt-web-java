package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和激活码绑定关系
 * @TableName front_user_activation_code_rel
 */
@TableName(value ="front_user_activation_code_rel")
@Data
@Builder
public class FrontUserActivationCodeRel implements Serializable {
    /**
     * 用户ID
     */
    @TableId
    private Integer userId;

    /**
     * 激活码ID
     */
    private Long activationCodeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}