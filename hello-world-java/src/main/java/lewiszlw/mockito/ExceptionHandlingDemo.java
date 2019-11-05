package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlingDemo {

    @InjectMocks
    private MathApplication mathApplication;

    private CalculatorService calculatorService;

    @Before
    public void setUp() throws Exception {
        calculatorService = mock(CalculatorService.class);
    }

    @Test(expected = RuntimeException.class)
    public void testAdd() {
        doThrow(new RuntimeException("Add operation not implemented"))
                .when(calculatorService).add(anyDouble(), anyDouble());
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
    }
}
