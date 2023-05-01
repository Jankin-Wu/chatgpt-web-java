package com.hncboy.chatgpt.admin.service;

import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author Jankin Wu
* @description 针对表【activation_code(激活码)】的数据库操作Service
* @createDate 2023-05-01 13:25:08
*/
public interface ActivationCodeService extends IService<ActivationCode> {

    Set<String> genCode(GenCodeRequest request);
}
