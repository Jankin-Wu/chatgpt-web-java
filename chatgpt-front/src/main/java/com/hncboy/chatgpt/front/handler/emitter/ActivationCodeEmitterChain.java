package com.hncboy.chatgpt.front.handler.emitter;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.hncboy.chatgpt.base.domain.entity.ActivationCode;
import com.hncboy.chatgpt.base.handler.ActivationCodeHandler;
import com.hncboy.chatgpt.base.util.ActivationCodeGenerator;
import com.hncboy.chatgpt.base.util.DateUtil;
import com.hncboy.chatgpt.base.util.ObjectMapperUtil;
import com.hncboy.chatgpt.base.util.StringUtils;
import com.hncboy.chatgpt.front.domain.request.ChatProcessRequest;
import com.hncboy.chatgpt.front.domain.vo.ChatReplyMessageVO;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author Jankin Wu
 * @description 激活码处理
 * @date 2023/5/1 15:07
 */
public class ActivationCodeEmitterChain extends AbstractResponseEmitterChain{

    @Override
    public void doChain(ChatProcessRequest request, ResponseBodyEmitter emitter) {
        boolean isEnabled = false;
        try {
            int userId = NumberUtil.parseInt(String.valueOf(StpUtil.getLoginId()));
            ActivationCodeHandler activationCodeHandler = SpringUtil.getBean(ActivationCodeHandler.class);
            // 校验当前用户激活码是否有效
            isEnabled = activationCodeHandler.verifyActivationCode(userId);
            if (Boolean.FALSE.equals(isEnabled)) {
                ChatReplyMessageVO chatReplyMessageVO = ChatReplyMessageVO.onEmitterChainException(request);
                String prompt = request.getPrompt();
                // 如果用户绑定的激活码无效，判断提示词格式是否为验证码格式
                prompt = StringUtils.removeSpacesAndBlankLines(prompt);
                if (!ActivationCodeGenerator.isActivationCodeFormat(prompt)) {
                    chatReplyMessageVO.setText("此账号未绑定激活码，或绑定的激活码已失效，请联系管理员（微信：JankinWu_）购买激活码。获取到新的激活码后，复制到输入框发送即可激活。");
                    emitter.send(ObjectMapperUtil.toJson(chatReplyMessageVO));
                    emitter.complete();
                    return;
                }
                // 如果是激活码格式，则判断激活码是否可用
                if (!activationCodeHandler.isAvailable(prompt, userId)) {
                    chatReplyMessageVO.setText("此激活码不可用，请输入正确的激活码。");
                    emitter.send(ObjectMapperUtil.toJson(chatReplyMessageVO));
                    emitter.complete();
                    return;
                }
                // 如果激活码可用，则为用户绑定新的激活码
                ActivationCode activationCode = activationCodeHandler.bindActivationCodeToUser(prompt, userId);
                String expirationTimeStr = DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss", activationCode.getExpirationTime());
                chatReplyMessageVO.setText(StrUtil.format("泰裤辣！现在您可以畅享程序全部功能！非常感谢您的支持！您的账户有效期为{}个月，将在 {} 到期，如有任何问题或疑虑，请联系管理员微信：JankinWu_" +
                        "\n（请忽略下面的报错信息）", activationCode.getValidityPeriod(), expirationTimeStr));
                emitter.send(ObjectMapperUtil.toJson(chatReplyMessageVO));
                emitter.complete();
                return;
            }
            if (getNext() != null) {
                getNext().doChain(request, emitter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
