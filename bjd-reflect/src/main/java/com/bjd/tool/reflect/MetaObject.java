package com.bjd.tool.reflect;

/**
 * Hello world!
 *
 */
public class MetaObject {

    private final Object originalObject;
    private final ObjectWrapper objectWrapper;
    private final ReflectorFactory reflectorFactory;

    public MetaObject(Object originalObject,ReflectorFactory reflectorFactory) {
        this.originalObject = originalObject;
        this.reflectorFactory = reflectorFactory;
        if(originalObject instanceof ObjectWrapper){
            this.objectWrapper = (ObjectWrapper)originalObject;
        }else{
            this.objectWrapper = new BeanWrapper();
        }

    }

    public static MetaObject forObject(Object originalObject) throws Exception {
        nullProcess(originalObject);
        return new MetaObject(originalObject,new DefaultReflectorFactory());
    }

    public ReflectorFactory getReflectorFactory() {
        return reflectorFactory;
    }

    public Object getOriginalObject() {
        return originalObject;
    }

    public ObjectWrapper getObjectWrapper() {
        return objectWrapper;
    }

    public void setValue(){

    }

    private static void nullProcess(Object originalObject) throws Exception {
        if(null == originalObject){
            throw new Exception("originalObject must not null");
        }
    }

}