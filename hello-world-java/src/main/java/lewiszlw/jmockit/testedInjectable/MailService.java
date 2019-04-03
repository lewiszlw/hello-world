package lewiszlw.jmockit.testedInjectable;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public interface MailService {
    /**
     * 发送邮件
     *
     * @param userId
     *            邮件接受人id
     * @param content
     *            邮件内容
     * @return 发送成功了，就返回true,否则返回false
     */
    public boolean sendMail(long userId, String content);
}
