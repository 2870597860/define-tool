package com.bjd.tool.reflect;

import static org.junit.Assert.assertTrue;

import com.bjd.tool.reflect.core.DefaultReflectorFactory;
import com.bjd.tool.reflect.core.MetaObject;
import com.bjd.tool.reflect.core.Reflector;
import com.bjd.tool.reflect.entity.User;
import com.bjd.tool.reflect.util.ClazzLoaderUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        List<Class> allClass = ClazzLoaderUtil.getAllClassByPackageName("com.bjd.tool.reflect.entity");
        for (Class aClass : allClass) {
            DefaultReflectorFactory.getInstance().initCacheClass(aClass);
        }

        metaObject.setValue("name","xiaodai");

        System.out.println(user.getName());
    }
}
