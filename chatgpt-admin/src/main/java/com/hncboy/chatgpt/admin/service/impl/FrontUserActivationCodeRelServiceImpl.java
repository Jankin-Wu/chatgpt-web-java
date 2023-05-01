package com.hncboy.chatgpt.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.admin.service.FrontUserActivationCodeRelService;
import com.hncboy.chatgpt.base.domain.entity.FrontUserActivationCodeRel;
import com.hncboy.chatgpt.base.mapper.FrontUserActivationCodeRelMapper;
import org.springframework.stereotype.Service;

/**
* @author Jankin Wu
* @description 针对表【front_user_activation_code_rel(用户和激活码绑定关系)】的数据库操作Service实现
* @createDate 2023-05-01 13:26:39
*/
@Service
public class FrontUserActivationCodeRelServiceImpl extends ServiceImpl<FrontUserActivationCodeRelMapper, FrontUserActivationCodeRel>
    implements FrontUserActivationCodeRelService {

}




