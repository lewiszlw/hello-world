package lewiszlw.javase.newclone;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/21
 * Time:14:40
 */
public class SimpleObject implements Cloneable {

    private int id;
    private String name;

    public SimpleObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public SimpleObject clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (SimpleObject) clone;
    }
}
