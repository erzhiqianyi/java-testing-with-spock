package top.erzhiqian.spock.sample;

import org.junit.Test;
import top.erzhiqian.spock.sample.math.Adder;

import static org.junit.Assert.*;

public class AdderTest {

    @Test
    public void add() {
        Adder adder = new Adder();
        assertEquals("1 + 1 is 2",2, (adder.add(1,1)));
    }

    @Test
    public void order(){
        Adder adder = new Adder();
        assertEquals("Order does not matter ",5,adder.add(2, 3));
        assertEquals("Order does not matter ",5,adder.add(3, 2));
    }
}