package com.wy.password;


import com.wy.common.error.BusinessException;
import com.wy.service.MemberService;
import com.wy.service.model.MemberModel;
import com.wy.utils.EncryptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {


    @Autowired
    private MemberService memberService;


    @Test
    public void passwordTest() throws BusinessException {

        MemberModel memberModel = memberService.selectMemberInfoByPhone("15304727515");

        System.out.println(memberModel.getPassword());

        if (new BCryptPasswordEncoder().matches(memberModel.getPassword(), EncryptionUtil.encrypt("0"))){
            System.out.println(123);
        }
    }
}
