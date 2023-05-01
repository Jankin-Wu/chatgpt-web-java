package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 激活码
 *
 * @author Jankin Wu
 * @TableName activation_code
 */
@TableName(value = "activation_code")
@Data
@Builder
public class ActivationCode implements Serializable {
    /**
     * 激活码ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 激活码
     */
    private String code;

    /**
     * 状态（0：未分发未使用，1：已分发未激活，2：已使用）
     */
    private Integer status;

    /**
     * 生成时间
     */
    private Date genTime;

    /**
     * 激活时间
     */
    private Date activationTime;

    /**
     * 有效期（单位：月）
     */
    private Integer validityPeriod;

    /**
     * 到期时间
     */
    private Date expirationTime;

    /**
     * 是否可用（0：不可用，1：可用）
     */
    private Integer isEnabled;

    /**
     * 邀请者
     */
    private String inviter;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static boolean judgeExit(String activationCode, List<ActivationCode> codeList) {
        return codeList.stream().anyMatch(i -> Objects.equals(i.getCode(), activationCode));
    }
}