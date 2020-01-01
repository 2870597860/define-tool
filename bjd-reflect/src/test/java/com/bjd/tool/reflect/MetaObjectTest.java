package com.bjd.tool.reflect;

import static org.junit.Assert.assertTrue;

import com.bjd.tool.reflect.core.MetaObject;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MetaObjectTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void reflectDemo() throws Exception {
        final User user = new User();
        MetaObject metaObject = MetaObject.forObject(user);
        metaObject.setValue("name","xiaodai");

        System.out.println(user.getName());
    }
}
