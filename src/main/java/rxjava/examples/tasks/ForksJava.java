package rxjava.examples.tasks;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class ForksJava extends RecursiveAction {
    SlowTasks[] slowTasks;

    public ForksJava(SlowTasks[] slowTasks) {
        this.slowTasks = slowTasks;
    }

    @Override
    protected void compute() {
      if(slowTasks.length == 1) {
          slowTasks[0].process();
      }else {
        int size = slowTasks.length;
        SlowTasks[] slowTasks2 = Arrays.copyOfRange(slowTasks, 0 , size/2);
        SlowTasks[] slowTasks3 = Arrays.copyOfRange(slowTasks,  size/2, size);
        invokeAll(new ForksJava(slowTasks2), new ForksJava(slowTasks3));
      }
    }
}
