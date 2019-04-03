package lewiszlw.jmockit.testedInjectable;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public interface UserCheckService {
    /**
     * 校验某个用户是否是合法用户
     *
     * @param userId
     *            用户ID
     * @return 合法的就返回true,否则返回false
     */
    public boolean check(long userId);
}
