package com.hncboy.chatgpt;

import com.hncboy.chatgpt.admin.domain.request.GenCodeRequest;
import com.hncboy.chatgpt.admin.service.ActivationCodeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

/**
 * @author Jankin Wu
 * @description
 * @date 2023/5/1 18:22
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActivationCodeServiceTest {

    @Autowired
    private ActivationCodeService activationCodeService;

    @Test
    void genCode() {
        GenCodeRequest request = new GenCodeRequest();
        request.setCount(5);
        request.setNumMonths(6);
        Set<String> codes = activationCodeService.genCode(request);
        for (String code : codes) {
            System.out.println(code);
        }
    }
}
