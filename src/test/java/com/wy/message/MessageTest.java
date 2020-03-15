package com.wy.message;


import com.wy.service.manager.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {

    @Test
    public void messageSendTest(){
        Message.sendMessageByMobilePhone("15304727515","123456");
    }
}
