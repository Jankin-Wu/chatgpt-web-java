package com.hncboy.chatgpt.base.mapper;

import com.hncboy.chatgpt.base.domain.entity.FrontUserActivationCodeRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Jankin Wu
* @description 针对表【front_user_activation_code_rel(用户和激活码绑定关系)】的数据库操作Mapper
* @createDate 2023-05-01 13:26:39
* @Entity com.hncboy.chatgpt.base.domain.entity.FrontUserActivationCodeRel
*/
@Mapper
public interface FrontUserActivationCodeRelMapper extends BaseMapper<FrontUserActivationCodeRel> {

}




