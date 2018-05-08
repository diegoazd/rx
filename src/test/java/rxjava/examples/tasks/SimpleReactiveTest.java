package rxjava.examples.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleReactiveTest {

    @Test
    public void shouldSumReactiveXtensions() {
        SimpleReactive simpleReactive = new SimpleReactive();

        for(int i=0; i<2500;i++) {
            simpleReactive.sum();
        }

        assertEquals(2500,simpleReactive.value);
    }

}