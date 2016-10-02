package com.github.model;

/**
 * Copyright & Author
 * michal
 */
public class Tuple<L, P> {

    private L left;
    private P right;

    public Tuple(L left, P right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public P getRight() {
        return right;
    }

    public void setRight(P right) {
        this.right = right;
    }
}
