package com.bjd.tool.reflect;

import com.bjd.tool.reflect.exception.ReflectionException;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflector {
    private final Class<?> type;
    private final MethodAccess methodAccess;
    private final List<String> readablePropertyNames = new ArrayList<>();
    private final List<String> writeablePropertyNames= new ArrayList<>();
    private final Map<String, MethodInfo> getMethods = new HashMap();
    private final Map<String, MethodInfo> setMethods = new HashMap();

    public Reflector(Class<?> type) {
        this.type = type;
        this.methodAccess = MethodAccess.get(type);
        addMethodIndex();
    }
    private void addMethodIndex(){
        String[] methodNames = methodAccess.getMethodNames();
        Class[][] parameterTypes = methodAccess.getParameterTypes();
        String name = "";
        for (int i = 0; i < methodNames.length; i++) {
            String methodName = methodNames[i];
            name = PropertyNamer.methodToProperty(methodName);

            if ((methodName.startsWith("get") && methodName.length() > 3)
                    || (methodName.startsWith("is") && methodName.length() > 2)) {

                getMethods.put(name,new MethodInfo(i,methodName,name));
                readablePropertyNames.add(i,name);
                continue;
            }

            if (name.startsWith("set") && name.length() > 3) {
                if (parameterTypes[i].length == 1) {
                    setMethods.put(name,new MethodInfo(i,methodName,name));
                    writeablePropertyNames.add(i,name);
                }
                continue;
            }

        }
    }
    public MethodAccess findMethodAccess(){
        return this.methodAccess;
    }

    public MethodInfo findGetMethodInfo(String propertyName){
        MethodInfo methodInfo = getMethods.get(propertyName);
        if (methodInfo == null) {
            throw new ReflectionException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
        }
        return methodInfo;
    }
    public MethodInfo findSetMethodInfo(String propertyName){
        MethodInfo methodInfo = setMethods.get(propertyName);
        if (methodInfo == null) {
            throw new ReflectionException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
        }
        return methodInfo;
    }
}

