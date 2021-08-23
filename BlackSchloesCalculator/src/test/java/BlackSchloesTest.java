import org.junit.Assert;
import org.junit.Test;

import java.util.InputMismatchException;

import static calculator.BlackSchloesUtil.calculateCall;
import static calculator.BlackSchloesUtil.calculatePut;


public class BlackSchloesTest {

    private static final double DELTA = 0.01;

    @Test
    public void testCalculateOptionPrice() {

        double callPrice = calculateCall(100, 95, 0.25, 50, 10);
        double putPrice = calculatePut(100, 95, 0.25, 50, 10);

        Assert.assertEquals(13.70, callPrice, DELTA);
        Assert.assertEquals(6.35, putPrice, DELTA);
    }

    @Test(expected = AssertionError.class)
    public void testFailCalculateCallPrice() {

        double callPrice = calculateCall(150, 95, 0.25, 50, 10);
        Assert.assertEquals(13.70, callPrice, DELTA);

    }

    @Test(expected = AssertionError.class)
    public void testFailCalculatePutPrice() {

        double putPrice = calculatePut(160, 95, 0.25, 50, 10);
        Assert.assertEquals(6.35, putPrice, DELTA);
    }

    @Test(expected = InputMismatchException.class)
    public void testWrongInputSpotPrice() {

        double callPrice = calculateCall(0, 95, 0.25, 50, 10);

    }

    @Test(expected = InputMismatchException.class)
    public void testWrongInputSigma() {

        double callPrice = calculateCall(0, 95, 0.25, 0, 10);

    }


}
