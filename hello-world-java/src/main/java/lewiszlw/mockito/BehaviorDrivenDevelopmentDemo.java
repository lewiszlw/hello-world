package lewiszlw.mockito;

import lewiszlw.mockito.application.CalculatorService;
import lewiszlw.mockito.application.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

// Behavior Driven Development is a style of writing tests
// uses given, when and then format as test methods.

@RunWith(MockitoJUnitRunner.class)
public class BehaviorDrivenDevelopmentDemo {


    @InjectMocks
    private MathApplication mathApplication;

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {

        // given
        given(calculatorService.add(20.0, 10.0)).willReturn(30.0);

        // when
        double result = mathApplication.add(20.0, 10.0);

        // then
        Assert.assertEquals(result,30.0, 0.0);
    }
}
