package org.apsapeh.General.Wrappers;

public class Wrapper <T> {
    private T obj;

    public Wrapper(T obj) {
        this.obj = obj;
    }

    public void set(T obj) {
        this.obj = obj;
    };

    public T get() {
        return this.obj;
    }
}
