package com.hncboy.chatgpt.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.service.ActivationCodeService;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.hncboy.chatgpt.base.mapper.ActivationCodeMapper;
import com.hncboy.chatgpt.base.util.ActivationCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author Jankin Wu
* @description 针对表【activation_code(激活码)】的数据库操作Service实现
* @createDate 2023-05-01 13:25:08
*/
@RequiredArgsConstructor
@Service
public class ActivationCodeServiceImpl extends ServiceImpl<ActivationCodeMapper, ActivationCode>
    implements ActivationCodeService {

    private final ActivationCodeGenerator activationCodeGenerator;

    @Override
    public Set<String> genCode(GenCodeRequest request) {
        Set<String> codes = activationCodeGenerator.generateCodes(request.getCount());
        List<ActivationCode> activationCodeList = codes
                .stream()
                .map(item -> ActivationCode.builder().code(item).genTime(new Date()).inviter("admin").validityPeriod(request.getNumMonths()).build())
                .collect(Collectors.toList());
        saveBatch(activationCodeList);
        return codes;
    }
}




