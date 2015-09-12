package me.mattlogan.rhymecity.data.util;

import android.os.NetworkOnMainThreadException;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Unsubscribing from an RxJava subscription to a Retrofit call throws a
 * NetworkOnMainThreadException because Call.cancel() is seen by Android as a "network operation"
 * even though it's super quick and perfectly fine to do on the main thread. So we can just catch
 * those exceptions and move right along.
 */
public final class SafeCompositeSubscription {

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public void add(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void clear() {
        try {
            subscriptions.clear();
        } catch (NetworkOnMainThreadException exception) {
            // Ignore
        }
    }
}
