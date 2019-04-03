package lewiszlw.jmockitexample.capturing;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public interface IPrivilege {

    /**
     * 判断用户有没有权限
     *
     * @param userId
     * @return 有权限，就返回true,否则返回false
     */
    public boolean isAllow(long userId);
}
