package com.hncboy.chatgpt.base.mapper;

import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Jankin Wu
* @description 针对表【activation_code(激活码)】的数据库操作Mapper
* @createDate 2023-05-01 13:25:08
* @Entity com.hncboy.chatgpt.base.domain.entity.ActivationCode
*/
@Mapper
public interface ActivationCodeMapper extends BaseMapper<ActivationCode> {

}




