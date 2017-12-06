package rxjava.examples.tasks;

public class SlowTasks {

    public void process() {
        try {
            Thread.sleep(250);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
