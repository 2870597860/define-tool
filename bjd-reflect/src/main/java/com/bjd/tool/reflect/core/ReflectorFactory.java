package com.bjd.tool.reflect.core;

public interface ReflectorFactory {

    boolean isClassCacheEnabled();

    Reflector findForClass(Class<?> type);

    void initCacheClass(Class<?> type);
}




