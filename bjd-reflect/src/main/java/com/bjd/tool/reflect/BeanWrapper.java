package com.bjd.tool.reflect;

public class BeanWrapper implements ObjectWrapper{

    private final Object bean;

    private final MetaClass metaClass;

    public BeanWrapper(Object bean, MetaObject metaObject) {
        this.bean = bean;
        this.metaClass = MetaClass.forClass(bean.getClass(),metaObject.getReflectorFactory());
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void set() {

    }
}
