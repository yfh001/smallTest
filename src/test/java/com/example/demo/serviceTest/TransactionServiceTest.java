package com.example.demo.serviceTest;

import com.example.demo.TransactionManageApplication;
import com.example.demo.utils.GlobalUniqueId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionManageApplication.class)
public class TransactionServiceTest {

    @Autowired
    GlobalUniqueId globalUniqueId;

    @Test
    public void testGuid(){
      String result =  globalUniqueId.generateGuid();
      System.out.println(result);
    }
}
