package com.hncboy.chatgpt.admin.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author Jankin Wu
 * @description
 * @date 2023/5/13 16:35
 */
@Data
@Schema(title = "激活码展示参数")
public class ActivationCodeVO {

    /**
     * 激活码ID
     */
    @Schema(title = "ID")
    private long id;

    /**
     * 激活码
     */
    @Schema(title = "激活码")
    private String code;

    /**
     * 状态（0：未分发未使用，1：已分发未激活，2：已使用）
     */
    @Schema(title = "状态（0：未分发未使用，1：已分发未激活，2：已使用）")
    private Integer status;

    /**
     * 生成时间
     */
    @Schema(title = "生成时间")
    private Date genTime;

    /**
     * 激活时间
     */
    @Schema(title = "激活时间")
    private Date activationTime;

    /**
     * 有效期（单位：月）
     */
    @Schema(title = "有效期（单位：月）")
    private Integer validityPeriod;

    /**
     * 到期时间
     */
    @Schema(title = "到期时间")
    private Date expirationTime;

    /**
     * 是否可用（0：不可用，1：可用）
     */
    @Schema(title = "是否可用（0：不可用，1：可用）")
    private Integer isEnabled;

    /**
     * 邀请者
     */
    @Schema(title = "是否可用（0：不可用，1：可用）")
    private String inviter;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remarks;

    /**
     * 更新时间
     */
    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
