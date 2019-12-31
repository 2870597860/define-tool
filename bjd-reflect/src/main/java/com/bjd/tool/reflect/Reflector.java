package com.bjd.tool.reflect;

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
    private final Map<String, Integer> getMethods = new HashMap();
    private final Map<String, Integer> setMethods = new HashMap();
    private final Map<String, Integer> otherMethods = new HashMap();

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

                getMethods.put(name,i);
                readablePropertyNames.add(i,name);
                continue;
            }

            if (name.startsWith("set") && name.length() > 3) {
                if (parameterTypes[i].length == 1) {
                    setMethods.put(name,i);
                    writeablePropertyNames.add(i,name);
                }
                continue;
            }

            otherMethods.put(name,i);
        }
    }
// 获取方法包装类
    private Object get
}
