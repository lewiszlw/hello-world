package lewiszlw.javase.generictype;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/18
 */
public class GenericClass<T> {

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public void printTClassName() {
        System.out.println(t.getClass().getName());
    }

    public void printSth() {
        System.out.println("test");
    }
}
