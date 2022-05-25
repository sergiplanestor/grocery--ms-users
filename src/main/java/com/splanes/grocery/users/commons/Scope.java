package com.splanes.grocery.users.commons;

public class Scope {

    public interface NonResultFunction<T> {
        void exec(T obj);
    }

    public static <T> T apply(T obj, NonResultFunction<T> block) {
        block.exec(obj);
        return obj;
    }
}
