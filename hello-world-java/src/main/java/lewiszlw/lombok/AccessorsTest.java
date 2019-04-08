package lewiszlw.lombok;

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */
public class AccessorsTest {

    @Test
    public void testPersion1() {
        Person1 person1 = new Person1().age(1).name("lewiszlw");
        Assert.assertTrue(person1.age() == 1);
        Assert.assertTrue("lewiszlw".equals(person1.name()));
    }

    @Test
    public void testPersion2() {
        Person2 person2 = new Person2().setAge(1).setName("lewiszlw");
        Assert.assertTrue(person2.getAge() == 1);
        Assert.assertTrue("lewiszlw".equals(person2.getName()));
    }
}
