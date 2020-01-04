package com.bjd.tool.reflect.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultReflectorFactory implements ReflectorFactory{
    private boolean classCacheEnabled = true;
    private final ConcurrentMap<Class<?>, Reflector> reflectorMap = new ConcurrentHashMap<Class<?>, Reflector>();

    public static DefaultReflectorFactory getInstance(){
        return Inner.instance;
    }

    private DefaultReflectorFactory() {
    }

    private static class Inner{
        private static final DefaultReflectorFactory instance = new DefaultReflectorFactory();
    }

    @Override
    public boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }

    public DefaultReflectorFactory classCacheEnabled(boolean classCacheEnabled){
         this.classCacheEnabled = classCacheEnabled;
         return this;
    }

    @Override
    public Reflector findForClass(Class<?> type) {
        if (classCacheEnabled) {
            // synchronized (type) removed see issue #461
            Reflector cached = reflectorMap.get(type);
            if (cached == null) {
                cached = new Reflector(type);
                reflectorMap.put(type, cached);
            }
            return cached;
        } else {
            return new Reflector(type);
        }
    }

    @Override
    public void initCacheClass(Class<?> type) {
        Reflector cached = new Reflector(type);
        reflectorMap.put(type, cached);
    }
}
