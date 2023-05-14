package com.hncboy.chatgpt.admin.domain.query;

import com.hncboy.chatgpt.base.domain.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jankin Wu
 * @description 激活码分页查询
 * @date 2023/5/13 18:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(title = "聊天记录分页查询")
public class ActivationCodePageQuery extends PageQuery {

    /**
     * 激活码
     */
    @Schema(title = "激活码")
    private String code;
}
