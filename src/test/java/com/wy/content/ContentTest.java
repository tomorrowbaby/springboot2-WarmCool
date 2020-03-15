package com.wy.content;

import com.wy.dao.PanelContentDOMapper;
import com.wy.dataobject.PanelContentDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentTest {


    @Autowired
    PanelContentDOMapper panelContentDOMapper;
    @Test
    public void testContentAdd(){
        PanelContentDO panelContentDO = new PanelContentDO();

        panelContentDO.setPanelId(152);
        panelContentDO.setSortOrder(1);

        panelContentDOMapper.insertSelective(panelContentDO);
    }
}
