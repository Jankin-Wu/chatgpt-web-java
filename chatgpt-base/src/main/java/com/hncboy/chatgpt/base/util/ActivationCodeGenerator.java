package com.hncboy.chatgpt.base.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.hncboy.chatgpt.base.mapper.ActivationCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Jankin Wu
 * @description 邀请码生成器
 * @date 2023/4/30 22:54
 */
@Component
@RequiredArgsConstructor
public class ActivationCodeGenerator {

    private final ActivationCodeMapper activationCodeMapper;

    // 邀请码长度为8位
    private static final int CODE_LENGTH = 8;

    // 邀请码包含的字符
    private static final String CODE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public Set<String> generateCodes(int count) {
        Set<String> codes = new HashSet<>();
        List<ActivationCode> activationCodeList = activationCodeMapper.selectList(new QueryWrapper<>());
        while (codes.size() < count) {
            String code = generateActivationCode();
            // 如果当前邀请码没有被使用，则将其添加到集合中
            if (!ActivationCode.judgeExit(code, activationCodeList)) {
                codes.add(code);
            }
        }
        return codes;
    }

    private String generateActivationCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        Random random = new Random();
        while (sb.length() < CODE_LENGTH) {
            int index = random.nextInt(CODE_CHARS.length());
            char c = CODE_CHARS.charAt(index);
            // 判断当前字符是否已经添加到字符串中，避免生成重复字符
            if (!sb.toString().contains(String.valueOf(c))) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean isActivationCodeFormat(String input) {
        if (input == null) {
            return false;
        }
        String regex = "^[a-zA-Z\\d]{8}$";
        return input.matches(regex);
    }
}
