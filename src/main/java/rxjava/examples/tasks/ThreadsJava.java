package rxjava.examples.tasks;

public class ThreadsJava implements Runnable{

    SlowTasks slowTasks;

    public ThreadsJava(SlowTasks slowTasks) {
        this.slowTasks = slowTasks;
    }

    @Override
    public void run() {
        slowTasks.process();
    }
}
