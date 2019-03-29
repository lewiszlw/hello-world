package lewiszlw.javase.newclone;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/21
 * Time:14:54
 */
public class ComplexObject implements Cloneable {
    private int id;
    private String name;

    public ComplexObject(int id, String name) {
        this.id = id;
        this.name = id + name;
    }

    @Override
    public ComplexObject clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (ComplexObject) clone;
    }
}
