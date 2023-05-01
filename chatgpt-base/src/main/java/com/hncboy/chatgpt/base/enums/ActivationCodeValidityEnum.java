package com.hncboy.chatgpt.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jankin Wu
 * @description 激活码有效性枚举类
 * @date 2023/5/1 16:18
 */
@AllArgsConstructor
@Getter
public enum ActivationCodeValidityEnum {

    DISABLED(0),
    ENABLED(1);

    private final Integer code;
}
