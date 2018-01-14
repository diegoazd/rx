package rxjava.examples.controller;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import rxjava.examples.tasks.SlowTasks;


@Service
public class EventService {

    SlowTasks slowTasks = new SlowTasks();

    public void subscriber() {
        Observable.fromCallable(() -> {
            slowTasks.process();
            return "done";
        }).observeOn(Schedulers.single())
          .subscribeOn(Schedulers.io())
        .subscribe();
    }
}
