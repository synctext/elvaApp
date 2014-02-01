package org.elva.elvaapp;

/**
 * @author Aidan Follestad (afollestad)
 */
public interface ScrollStatePersister {

    public abstract int getScrollState();

    public abstract void setScrollState(int state);
}
