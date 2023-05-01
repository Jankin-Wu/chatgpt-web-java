package com.hncboy.chatgpt.base.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.hncboy.chatgpt.base.domain.entity.FrontUserActivationCodeRel;
import com.hncboy.chatgpt.base.enums.ActivationCodeStatusEnum;
import com.hncboy.chatgpt.base.enums.ActivationCodeValidityEnum;
import com.hncboy.chatgpt.base.mapper.ActivationCodeMapper;
import com.hncboy.chatgpt.base.mapper.FrontUserActivationCodeRelMapper;
import com.hncboy.chatgpt.base.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jankin Wu
 * @description 激活码处理
 * @date 2023/5/1 15:15
 */
@Slf4j
@Component
public class ActivationCodeHandler {

    private static FrontUserActivationCodeRelMapper frontUserActivationCodeRelMapper;

    private static ActivationCodeMapper activationCodeMapper;

    @Autowired
    public void setFrontUserActivationCodeRelMapper(FrontUserActivationCodeRelMapper frontUserActivationCodeRelMapper) {
        ActivationCodeHandler.frontUserActivationCodeRelMapper = frontUserActivationCodeRelMapper;
    }

    @Autowired
    public void setActivationCodeMapper(ActivationCodeMapper activationCodeMapper) {
        ActivationCodeHandler.activationCodeMapper = activationCodeMapper;
    }

    /**
     * 校验当前用户激活码是否有效
     *
     * @return 是否有效
     */
    public boolean verifyActivationCode(int userId) {
        FrontUserActivationCodeRel userActivationCodeRel = frontUserActivationCodeRelMapper.selectById(userId);
        // 如果用户没有添加邀请码
        if (Objects.isNull(userActivationCodeRel) || Objects.isNull(userActivationCodeRel.getActivationCodeId())) {
            log.warn("This user has not added an activation code yet, userId: {}", userId);
            return false;
        }
        ActivationCode activationCode = activationCodeMapper.selectById(userActivationCodeRel.getActivationCodeId());
        if (Objects.isNull(activationCode)) {
            return false;
        }
        // 判断激活码是否被禁用
        if (!activationCode.getIsEnabled().equals(ActivationCodeValidityEnum.ENABLED.getCode())) {
            log.warn("This activation code has expired or has been disabled, activationCode: {}, userId: {}", activationCode, userId);
            return false;
        }
        // 如果激活码已过期则设置此激活码为不可用
        if (activationCode.getExpirationTime().before(new Date())) {
            log.warn("This activation code has expired, activationCode: {}, userId: {}", activationCode, userId);
            activationCode.setIsEnabled(ActivationCodeValidityEnum.DISABLED.getCode());
            activationCodeMapper.updateById(activationCode);
            return false;
        }
        return true;
    }

    /**
     * 检验用户输入的激活码是否可用
     *
     * @param code 激活码
     * @return 是否可用
     */
    public boolean isAvailable(String code, int userId) {
        ActivationCode activationCode = activationCodeMapper.selectOne(new QueryWrapper<ActivationCode>().lambda().eq(ActivationCode::getCode, code));
        if (Objects.isNull(activationCode)) {
            log.warn("This activation code can not find in DB, activationCode: {}, userId: {}", code, userId);
            return false;
        }
        if (!ActivationCodeStatusEnum.DISTRIBUTED.getCode().equals(activationCode.getStatus())) {
            log.warn("This activation code is undistributed, activationCode: {}, userId: {}", code, userId);
            return false;
        }
        if (!ActivationCodeValidityEnum.ENABLED.getCode().equals(activationCode.getIsEnabled()) || Objects.nonNull(activationCode.getExpirationTime())) {
            log.warn("This activation code has expired or has been disabled, activationCode: {}, userId: {}", code, userId);
            return false;
        }
        return true;
    }

    /**
     * 给用户绑定激活码
     * @param code 激活码
     */
    @Transactional
    public ActivationCode bindActivationCodeToUser(String code, int userId) {
        ActivationCode activationCode = activationCodeMapper.selectOne(new QueryWrapper<ActivationCode>().lambda().eq(ActivationCode::getCode, code));
        FrontUserActivationCodeRel userCodeRel = frontUserActivationCodeRelMapper.selectById(userId);
        // 绑定用户和激活码的关系
        if (Objects.nonNull(userCodeRel)) {
            userCodeRel.setActivationCodeId(activationCode.getId());
            frontUserActivationCodeRelMapper.updateById(userCodeRel);
        } else {
            userCodeRel = FrontUserActivationCodeRel.builder().activationCodeId(activationCode.getId()).userId(userId).build();
            frontUserActivationCodeRelMapper.insert(userCodeRel);
        }
        // 修改激活码状态
        activationCode.setStatus(ActivationCodeStatusEnum.ACTIVATED.getCode());
        activationCode.setActivationTime(new Date());
        LocalDateTime expirationTime = LocalDateTime.now().plusMonths(activationCode.getValidityPeriod());
        activationCode.setExpirationTime(DateUtil.toDate(expirationTime));
        activationCodeMapper.updateById(activationCode);
        return activationCode;
    }
}
