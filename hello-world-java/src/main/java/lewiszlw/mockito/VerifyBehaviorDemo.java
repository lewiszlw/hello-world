package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VerifyBehaviorDemo {

    /**
     * @InjectMocks annotation is used to create and inject the mock object
     */
    @InjectMocks
    private MathApplication mathApplication;


    /**
     * @Mock annotation is used to create the mock object to be injected
     */
    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {
        // add the behavior of calc service to add two numbers
        when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);

        // test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);

        // equivalent to verify(calculatorService, times(1)).add(10.0, 20.0);
        verify(calculatorService).add(10.0, 20.0);
        verify(calculatorService, times(1)).add(10.0, 20.0);
    }

    @Test
    public void testAdd2() {
        when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);

        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);

        verify(calculatorService, times(3)).add(10.0, 20.0);

        verify(calculatorService, atLeastOnce()).add(10.0, 20.0);

        verify(calculatorService, atLeast(2)).add(10.0, 20.0);

        verify(calculatorService, atMost(3)).add(10.0, 20.0);

        verify(calculatorService, atMostOnce()).divide(10.0, 20.0);

        verify(calculatorService, never()).subtract(10.0, 20.0);
        verify(calculatorService, times(0)).multiply(10.0, 20.0);
    }

}
