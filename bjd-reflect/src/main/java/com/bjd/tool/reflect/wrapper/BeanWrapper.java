package com.bjd.tool.reflect.wrapper;

import com.bjd.tool.reflect.MethodInfo;
import com.bjd.tool.reflect.core.MetaClass;
import com.bjd.tool.reflect.core.MetaObject;
import com.esotericsoftware.reflectasm.MethodAccess;

public class BeanWrapper implements ObjectWrapper {

    private final Object bean;

    private final MetaClass metaClass;


    public BeanWrapper(Object bean, MetaObject metaObject) {
        this.bean = bean;
        this.metaClass = MetaClass.forClass(bean.getClass(),metaObject.getReflectorFactory());
    }

    @Override
    public void set(String propertyName,Object value) {
        MethodAccess methodAccess = metaClass.getMethodAccess();
        MethodInfo setMethInfo = metaClass.getSetMethInfo(propertyName);
        methodAccess.invoke(bean,setMethInfo.getIndex(),value);
    }

    @Override
    public Object get(String propertyName) {
        MethodAccess methodAccess = metaClass.getMethodAccess();
        MethodInfo getMethInfo = metaClass.getGetMethInfo(propertyName);
        return  methodAccess.invoke(bean,getMethInfo.getIndex());
    }
}
