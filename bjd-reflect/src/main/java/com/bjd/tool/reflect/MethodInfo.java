package com.bjd.tool.reflect;

public class MethodInfo {
    private int index;
    private String methodName;
    private String propertyName;

    public MethodInfo(int index, String methodName,String propertyName) {
        this.index = index;
        this.methodName = methodName;
        this.propertyName = propertyName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
