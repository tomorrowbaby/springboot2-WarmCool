package com.wy.item.category;

import com.wy.dao.ItemCategoryDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemCategoryTest {

    @Autowired
    private ItemCategoryDOMapper itemCategoryDOMapper;

    @Test
    public void testDel(){
        long id = 999569999;
        int row = itemCategoryDOMapper.deleteByPrimaryKey(id);
        System.out.println(row);
    }

}
