package rxjava.examples.tasks;

import java.util.logging.Logger;

public class SlowTasks {
    private static final Logger logger = Logger.getLogger("SlowTask");

    public void process() {
        try {
            logger.info("Excute slow task");
            Thread.sleep(250);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
