package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimeoutsDemo {

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

        // verify call to add method to be completed within 100 ms
        verify(calculatorService, timeout(100)).add(10.0, 20.0);
        verify(calculatorService, timeout(100).times(1)).add(10.0, 20.0);
    }
}
