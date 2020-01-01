package com.bjd.tool.reflect.core;

import com.bjd.tool.reflect.MethodInfo;
import com.esotericsoftware.reflectasm.MethodAccess;

public class MetaClass {
    private final ReflectorFactory reflectorFactory;
    private final Reflector reflector;

    private MetaClass(Class<?> type, ReflectorFactory reflectorFactory) {
        this.reflectorFactory = reflectorFactory;
        this.reflector = reflectorFactory.findForClass(type);
    }

    public static MetaClass forClass(Class<?> type, ReflectorFactory reflectorFactory) {
        return new MetaClass(type, reflectorFactory);
    }

    public MethodInfo getGetMethInfo(String name) {
        return reflector.findGetMethodInfo(name);
    }

    public MethodInfo getSetMethInfo(String name) {
        return reflector.findSetMethodInfo(name);
    }

    public MethodAccess getMethodAccess() {
        return reflector.findMethodAccess();
    }


}
