package rxjava.examples;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;

public class HelloWorld {

  public static void main(String[] args) throws InterruptedException {
      Flowable<String> source =  Flowable.fromCallable(() -> {
        Thread.sleep(1000);
        return "Done";
      });

      Flowable<String> runBackground = source.subscribeOn(Schedulers.io());
      Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());
      showForeground.subscribe(System.out::println, Throwable::printStackTrace);


      Thread.sleep(2000);

      Flowable.range(1, 10)
              .observeOn(Schedulers.computation())
              .map(v -> v * v)
              .blockingSubscribe(System.out::println);

      Flowable.range(1,10)
              .flatMap( v ->
              Flowable.just(v)
                      .subscribeOn(Schedulers.computation())
                      .map(w -> w * w)
              ).blockingSubscribe(System.out::println);

      Flowable.range(1, 10)
              .parallel()
              .runOn(Schedulers.computation())
              .map(v -> v * v)
              .sequential()
              .blockingSubscribe(System.out::println);

      System.out.println("-------------");


  }
}
