package com.hncboy.chatgpt.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodePageQuery;
import com.hncboy.chatgpt.admin.domain.query.ActivationCodeUpdateRequest;
import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.domain.vo.ActivationCodeVO;
import com.hncboy.chatgpt.admin.service.ActivationCodeService;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.hncboy.chatgpt.base.enums.ActivationCodeStatusEnum;
import com.hncboy.chatgpt.base.exception.ServiceException;
import com.hncboy.chatgpt.base.mapper.ActivationCodeMapper;
import com.hncboy.chatgpt.base.util.ActivationCodeGenerator;
import com.hncboy.chatgpt.base.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jankin Wu
 * @description 针对表【activation_code(激活码)】的数据库操作Service实现
 * @createDate 2023-05-01 13:25:08
 */
@Slf4j
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

    @Override
    public Page<ActivationCodeVO> pageActivationCode(ActivationCodePageQuery activationCodePageQuery) {
        Page<ActivationCode> page = page(new Page<>(activationCodePageQuery.getPageNum(), activationCodePageQuery.getPageSize()), new LambdaQueryWrapper<ActivationCode>()
                .eq(StrUtil.isNotBlank(activationCodePageQuery.getCode()), ActivationCode::getCode, activationCodePageQuery.getCode())
                .orderByDesc(ActivationCode::getGenTime));
        List<ActivationCodeVO> activationCodeVOList = page.getRecords().stream().map(item -> {
            ActivationCodeVO vo = new ActivationCodeVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return PageUtil.toPage(page, activationCodeVOList);
    }

    @Override
    public void updateActivationCode(ActivationCodeUpdateRequest request) {
        update(new LambdaUpdateWrapper<ActivationCode>()
                .set(StrUtil.isNotBlank(request.getRemarks()), ActivationCode::getRemarks, request.getRemarks())
                .set(Objects.nonNull(request.getStatus()), ActivationCode::getStatus, request.getStatus())
                .set(Objects.nonNull(request.getIsEnabled()), ActivationCode::getIsEnabled, request.getIsEnabled())
                .eq(ActivationCode::getId, request.getId()));
    }

    @Override
    public void distribute(Long id) {
        ActivationCode activationCode = getById(id);
        if (Objects.isNull(activationCode) || !ActivationCodeStatusEnum.UNDISTRIBUTED.getCode().equals(activationCode.getStatus())) {
            log.error("This activation code cannot be distributed, id: {}", id);
            throw new ServiceException(StrUtil.format("此激活码不可被分发, id: {}", id));
        }
        update(new LambdaUpdateWrapper<ActivationCode>()
                .set(Objects.nonNull(id), ActivationCode::getStatus, ActivationCodeStatusEnum.DISTRIBUTED.getCode())
                .eq(ActivationCode::getId, id));
    }
}




