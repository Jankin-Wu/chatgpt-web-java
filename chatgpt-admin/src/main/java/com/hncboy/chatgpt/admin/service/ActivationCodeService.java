package com.hncboy.chatgpt.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodePageQuery;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodeUpdateRequest;
import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.domain.vo.ActivationCodeVO;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;

import java.util.Set;

/**
* @author Jankin Wu
* @description 针对表【activation_code(激活码)】的数据库操作Service
* @createDate 2023-05-01 13:25:08
*/
public interface ActivationCodeService extends IService<ActivationCode> {

    Set<String> genCode(GenCodeRequest request);

    Page<ActivationCodeVO> pageActivationCode(ActivationCodePageQuery activationCodePageQuery);

    void updateActivationCode(ActivationCodeUpdateRequest request);

    void distribute(Long id);
}
