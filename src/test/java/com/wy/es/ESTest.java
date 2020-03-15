package com.wy.es;

import com.wy.common.error.BusinessException;
import com.wy.dataobject.es.SearchItemDO;
import com.wy.dao.repository.ESItemRepository;
import com.wy.service.ESItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {


    @Autowired
    ESItemRepository esItemRepository;


    @Autowired
    ESItemService esItemService;

    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    @Test
    public void esTest() throws BusinessException {

        esItemService.importAllItems();

        Iterable<SearchItemDO> searchItemDOIterable = esItemRepository.findAll();
        Iterator<SearchItemDO> itemDOIterable = searchItemDOIterable.iterator();

        while (itemDOIterable.hasNext()) {
            System.out.println(itemDOIterable.next());
        }




    }
}
