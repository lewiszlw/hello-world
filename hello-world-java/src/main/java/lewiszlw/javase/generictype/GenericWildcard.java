package lewiszlw.javase.generictype;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/18
 */
public class GenericWildcard {

    public static String queryStr(Class<? extends Number> cls) {
        return cls.getName();
    }
}
