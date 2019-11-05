package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CapturingArgumentsDemo {

    @InjectMocks
    private MathApplication mathApplication;

    @Mock
    private CalculatorService calculatorService;

    @Captor
    private ArgumentCaptor<Double> argumentCaptor2;

    @Test
    public void testAdd() {

        mathApplication.add(20.0, 10.0);

        ArgumentCaptor<Double> argumentCaptor1 = ArgumentCaptor.forClass(Double.class);
//        ArgumentCaptor<Double> argumentCaptor2 = ArgumentCaptor.forClass(Double.class);
        verify(calculatorService).add(argumentCaptor1.capture(), argumentCaptor2.capture());

        Assert.assertEquals(argumentCaptor1.getValue(), 20.0, 0.0);
        Assert.assertEquals(argumentCaptor2.getValue(), 10.0, 0.0);
    }
}
