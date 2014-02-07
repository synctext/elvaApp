package org.elva.elvaapp.cards;

/**
 * @author Aidan Follestad (afollestad)
 */
public interface ScrollStatePersister {

    public abstract int getScrollState();

    public abstract void setScrollState(int state);
}
