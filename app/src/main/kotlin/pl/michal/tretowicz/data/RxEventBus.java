package pl.michal.tretowicz.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Michał Trętowicz
 */
@Singleton
public class RxEventBus {

    private final PublishSubject<Object> mBusSubject;

    @Inject
    public RxEventBus() {
        mBusSubject = PublishSubject.create();
    }

    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    public Observable<Object> observable() {
        return mBusSubject;
    }

    public <T> Observable<T> filteredObservable(final Class<T> eventClass) {
        return mBusSubject.ofType(eventClass);
    }
}