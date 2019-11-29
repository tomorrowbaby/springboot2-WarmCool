package com.wy.item;


import com.wy.common.error.BusinessException;
import com.wy.dao.ItemDOMapper;
import com.wy.dataobject.ItemDO;
import com.wy.service.ItemService;
import com.wy.service.model.ItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Test
    public void testSelectItemCount() throws BusinessException {
        Long count = itemDOMapper.selectItemCount(1);
        System.out.println(count);
    }

    @Test
    public void testSetItemStatus() {
        ItemDO itemDO = new ItemDO();
        itemDO.setId(150642571432851L);
        itemDO.setStatus(0);
        itemDOMapper.updateByPrimaryKeySelective(itemDO);
    }
}
