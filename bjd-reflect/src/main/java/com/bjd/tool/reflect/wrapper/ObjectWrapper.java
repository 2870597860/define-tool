package com.bjd.tool.reflect.wrapper;

public interface ObjectWrapper {
    Object get(String propertyName);
    void set(String propertyName,Object value);
}
