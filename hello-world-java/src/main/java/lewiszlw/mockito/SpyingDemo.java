package lewiszlw.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpyingDemo {

    @Spy
    private List<Integer> spy = new ArrayList<>();

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        List<String> listSpy = spy(list);

        when(listSpy.size()).thenReturn(100);

        listSpy.add("one");
        listSpy.add("two");

        Assert.assertEquals(listSpy.get(0), "one");
        Assert.assertEquals(listSpy.get(1), "two");

        Assert.assertEquals(listSpy.size(), 100);

        verify(listSpy).add("one");
        verify(listSpy).add("two");
    }

    @Test
    public void test2() {
        when(spy.size()).thenReturn(100);

        spy.add(1);
        spy.add(2);

        Assert.assertEquals(spy.get(0), Integer.valueOf(1));
        Assert.assertEquals(spy.get(1), Integer.valueOf(2));

        Assert.assertEquals(spy.size(), 100);

        verify(spy).add(1);
        verify(spy).add(2);
    }
}
