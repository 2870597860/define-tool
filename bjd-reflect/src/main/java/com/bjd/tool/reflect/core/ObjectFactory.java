package com.bjd.tool.reflect.core;

import java.util.List;

public interface ObjectFactory {
    /**
     * Creates a new object with default constructor.
     * @param type Object type
     * @return
     */
    <T> T create(Class<T> type);

    /**
     * Creates a new object with the specified constructor and params.
     * @param type Object type
     * @param constructorArgTypes Constructor argument types
     * @param constructorArgs Constructor argument values
     * @return
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

}
