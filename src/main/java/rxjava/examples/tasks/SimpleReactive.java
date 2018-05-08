package rxjava.examples.tasks;


import io.reactivex.Observable;

public class SimpleReactive {
    int value = 0;

    public int sum() {
        Observable<Integer> observable = Observable.just(value);
        observable.subscribe(v -> {v++; value=v;});

        return value;
    }
}
