package rxjava.examples.tasks;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Reactive {
    public int processes = 0;
    public int after = 0;
    public final int MAX_RANGE = 500;
    SlowTasks slowTasks = new SlowTasks();

    Observer<Integer> consumer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Integer integer) {
            processes++;
            slowTasks.process();
            after++;
        }

        @Override
        public void onError(Throwable e) {
            System.out.println(e.getCause());
        }

        @Override
        public void onComplete() {
            System.out.println(processes);
            System.out.println(after);
        }
    };

    public void process() {
        Observable<Integer> source = Observable.range(1, MAX_RANGE);
        source.subscribe(consumer);
    }

    public void process2() {
       Observable.fromCallable(() -> {
           processes++;
           slowTasks.process();
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
