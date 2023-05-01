package com.hncboy.chatgpt.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jankin Wu
 * @description 激活码状态枚举类
 * @date 2023/5/1 17:02
 */
@Getter
@AllArgsConstructor
public enum ActivationCodeStatusEnum {

    /**
     * 未分发
     */
    UNDISTRIBUTED(0),

    /**
     * 已分发
     */
    DISTRIBUTED(1),

    /**
     * 已激活
     */
    ACTIVATED(2);

    private final Integer code;
}
