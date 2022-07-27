package com.yh;

import com.yh.yygh.common.utils.MD5;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainTest {

    @Test
    public void test(){
        long res = Long.parseLong("62d15e4040843c5b89901668");
        System.out.println(res);
    }

}
