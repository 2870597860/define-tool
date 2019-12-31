package com.bjd.tool.reflect;

@FunctionalInterface
public interface Invoker<P,R> {
    R invoke(P param) throws Exception;
}
