package rxjava.examples.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SlowTasksTest {
    private SlowTasks slowTasks;
    private long startTime;
    private long endTime;

    @Before
    public void setup() {
        slowTasks = new SlowTasks();
        startTime = System.nanoTime();
    }

    @After
    public void teardown() {
        endTime = System.nanoTime();
        System.out.println(TimeUnit.MILLISECONDS.toSeconds((endTime - startTime)/1000));
    }


    @Test
    public void shouldRunTask() {
        for(int i=0; i < 500; i++)
            slowTasks.process();
    }

    @Test
    public void shouldRunTaskWithThreads() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i=0; i < 500; i++) {
            Runnable worker = new ThreadsJava(new SlowTasks());
            executorService.execute(worker);
        }

        executorService.shutdown();
        executorService.awaitTermination(10l, TimeUnit.SECONDS);
    }

    @Test
    public void shouldRunTaskWithForks() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SlowTasks[] slowTasks = new SlowTasks[500];
        for(int i=0; i < 500; i++)
            slowTasks[i] = new SlowTasks();

        ForksJava forksJava = new ForksJava(slowTasks);
        pool.invoke(forksJava);
        forksJava.compute();
    }

    @Test
    public void shouldRunFlowable() throws InterruptedException {
        Reactive reactive = new Reactive();
        for(int i=0; i< 20000; i++) {
            reactive.process();
        }
        System.out.println("Before slow process: "+reactive.processes);
        System.out.println("Tasks processed: "+reactive.after);
        Thread.sleep(1000);
        System.out.println("Before slow process: "+reactive.processes);
        System.out.println("Tasks processed: "+reactive.after);
    }


}