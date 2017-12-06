package rxjava.examples.tasks;

import io.reactivex.Flowable;
import io.reactivex.schedulers.*;

public class Reactive {
    public int processes = 0;
    public int after = 0;

    public void process() {
        Flowable.fromCallable(() -> {
            processes++;
            new SlowTasks().process();
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(this::incrementAfter, Throwable::printStackTrace);

    }

    public void incrementAfter(String s) {
        after++;
    }
}
