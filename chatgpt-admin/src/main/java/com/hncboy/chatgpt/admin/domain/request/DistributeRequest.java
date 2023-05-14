package com.hncboy.chatgpt.admin.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Jankin Wu
 * @description 激活码分发请求
 * @date 2023/5/13 23:14
 */
@Schema(title = "激活码分发参数")
@Data
public class DistributeRequest {

    @Schema(title = "激活码ID")
    private Long id;
}
