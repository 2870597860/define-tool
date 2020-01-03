package com.bjd.tool.reflect.core;

import com.bjd.tool.reflect.exception.ReflectionException;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import static java.util.Objects.isNull;

public class DefaultObjectFactory implements ObjectFactory, Serializable {

    @Override
    public <T> T create(Class<T> type) {
        return create(type,null,null);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return newInstance(type, constructorArgTypes, constructorArgs);
    }

    private <T> T newInstance(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs){
        try {
            if(isNull(constructorArgTypes) || isNull(constructorArgs)) {
                Constructor<T> declaredConstructor = type.getDeclaredConstructor();
                if(!declaredConstructor.isAccessible()){
                    declaredConstructor.setAccessible(true);
                }
                return declaredConstructor.newInstance();
            }
            Constructor<T> constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
            if(!constructor.isAccessible()){
                constructor.setAccessible(true);
            }
            return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
        } catch (Exception e) {
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null && !constructorArgTypes.isEmpty()) {
                for (Class<?> argType : constructorArgTypes) {
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }
                argTypes.deleteCharAt(argTypes.length() - 1); // remove trailing ,
            }
            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null && !constructorArgs.isEmpty()) {
                for (Object argValue : constructorArgs) {
                    argValues.append(String.valueOf(argValue));
                    argValues.append(",");
                }
                argValues.deleteCharAt(argValues.length() - 1); // remove trailing ,
            }
            throw new ReflectionException("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + e, e);
        }
    }
}
