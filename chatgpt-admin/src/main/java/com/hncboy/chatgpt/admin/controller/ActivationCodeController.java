package com.hncboy.chatgpt.admin.controller;

import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.service.ActivationCodeService;
import com.hncboy.chatgpt.base.annotation.ApiAdminRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jankin Wu
 * @description 激活码
 * @date 2023/5/1 13:41
 */
@Tag(name = "管理端-聊天室相关接口")
@RequiredArgsConstructor
@ApiAdminRestController("/activation_code")
public class ActivationCodeController {

    private final ActivationCodeService activationCodeService;

    @Operation(summary = "激活码生成")
    @PostMapping("/gen_code")
    public void genCode(@RequestBody GenCodeRequest request) {
        activationCodeService.genCode(request);
    }
}
