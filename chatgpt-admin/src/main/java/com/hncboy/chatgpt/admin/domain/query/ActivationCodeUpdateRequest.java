package com.hncboy.chatgpt.admin.domain.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Jankin Wu
 * @description 激活码修改参数
 * @date 2023/5/13 20:42
 */
@Data
@Schema(title = "激活码修改参数")
public class ActivationCodeUpdateRequest {

    /**
     * 激活码ID
     */
    @Schema(title = "ID")
    private Long id;

    /**
     * 状态（0：未分发未使用，1：已分发未激活，2：已使用）
     */
    @Schema(title = "状态（0：未分发未使用，1：已分发未激活，2：已使用）")
    private Integer status;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remarks;

    /**
     * 是否可用（0：不可用，1：可用）
     */
    @Schema(title = "是否可用（0：不可用，1：可用）")
    private Integer isEnabled;
}
