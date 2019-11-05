package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResettingMock {

    @InjectMocks
    private MathApplication mathApplication;

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {

        when(calculatorService.add(20.0, 10.0)).thenReturn(30.0);

        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0.0);

        reset(calculatorService);

        // will throw a AssertionError
        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0.0);
    }
}
