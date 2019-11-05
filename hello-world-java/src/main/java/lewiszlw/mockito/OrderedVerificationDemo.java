package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderedVerificationDemo {

    @InjectMocks
    private MathApplication mathApplication;

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAddAndSubtract() {
        when(calculatorService.add(20.0, 10.0)).thenReturn(30.0);
        when(calculatorService.subtract(20.0, 10.0)).thenReturn(10.0);

        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0.0);
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0.0);

        InOrder inOrder = inOrder(calculatorService);

        // war called first
        inOrder.verify(calculatorService).add(20.0, 10.0);
        // war called second
        inOrder.verify(calculatorService).subtract(20.0, 10.0);
    }
}
