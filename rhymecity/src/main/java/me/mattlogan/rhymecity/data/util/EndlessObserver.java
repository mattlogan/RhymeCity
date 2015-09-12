package me.mattlogan.rhymecity.data.util;

import rx.Observer;

/**
 * Yep I got this from u2020.
 */
public abstract class EndlessObserver<T> implements Observer<T> {
    @Override
    public void onCompleted() {
    }
}
