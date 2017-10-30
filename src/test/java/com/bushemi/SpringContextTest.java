package com.bushemi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created by igor on 30.10.17.
 *
 * @Version 1.0
 */
@ContextConfiguration(locations = "classpath:app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringContextTest {
    @Test
    public void springContextTest(){
        Assert.isTrue((2*2)==4,"Launching of spring context was failed.");
    }
}
