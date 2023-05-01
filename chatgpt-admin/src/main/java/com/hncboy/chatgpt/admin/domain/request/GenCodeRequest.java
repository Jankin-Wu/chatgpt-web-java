package com.hncboy.chatgpt.admin.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Jankin Wu
 * @description 邀请码生成参数
 * @date 2023/5/1 13:54
 */
@Schema(title = "邀请码生成参数")
@Data
public class GenCodeRequest {

    private Integer count;

    private Integer numMonths;
}
