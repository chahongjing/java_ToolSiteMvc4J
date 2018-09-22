package com.zjy.baseframework;

public class NameValuePair<T, K> {
    private T name;
    private K value;

    public NameValuePair(T name, K value) {
        this.name = name;
        this.value = value;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }
}
