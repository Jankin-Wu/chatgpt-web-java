package com.hncboy.chatgpt.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodePageQuery;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodeUpdateRequest;
import com.hncboy.chatgpt.admin.domain.request.DistributeRequest;
import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.domain.vo.ActivationCodeVO;
import com.hncboy.chatgpt.admin.service.ActivationCodeService;
import com.hncboy.chatgpt.base.annotation.ApiAdminRestController;
import com.hncboy.chatgpt.base.handler.response.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jankin Wu
 * @description 激活码
 * @date 2023/5/1 13:41
 */
@Tag(name = "管理端-激活码相关接口")
@RequiredArgsConstructor
@ApiAdminRestController("/activation_code")
public class ActivationCodeController {

    private final ActivationCodeService activationCodeService;

    @Operation(summary = "激活码生成")
    @PostMapping("/gen_code")
    public R<Void> genCode(@RequestBody GenCodeRequest request) {
        activationCodeService.genCode(request);
        return R.success("生成激活码成功");
    }

    @Operation(summary = "激活码分页")
    @GetMapping("/page")
    public R<Page<ActivationCodeVO>> page(ActivationCodePageQuery activationCodePageQuery) {
        return R.success(activationCodeService.pageActivationCode(activationCodePageQuery));
    }

    @Operation(summary = "激活码修改")
    @PutMapping()
    public R<Void> update(ActivationCodeUpdateRequest request) {
        activationCodeService.updateActivationCode(request);
        return R.success("修改成功");
    }

    @Operation(summary = "激活码分发")
    @PostMapping("/distribute")
    public R<Void> distribute(@RequestBody DistributeRequest request) {
        activationCodeService.distribute(request.getId());
        return R.success("分发成功");
    }

}
