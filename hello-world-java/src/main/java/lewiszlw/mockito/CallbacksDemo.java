package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallbacksDemo {

    @InjectMocks
    private MathApplication mathApplication;

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {
        when(calculatorService.add(20.0, 10.0)).thenAnswer(new Answer<Double>() {
            @Override
            public Double answer(InvocationOnMock invocationOnMock) throws Throwable {
                // get the arguments passed to mock
                Object[] arguments = invocationOnMock.getArguments();
                for (Object arg : arguments) {
                    System.out.print(arg + " ");
                }

                // get the mock
                Object mock = invocationOnMock.getMock();
                System.out.println(mock);

                return 30.0;
            }
        });

        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0.0);
    }
}
