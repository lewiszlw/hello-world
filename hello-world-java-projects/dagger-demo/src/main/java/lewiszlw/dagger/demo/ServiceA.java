package lewiszlw.dagger.demo;

import javax.inject.Inject;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-13
 */
public class ServiceA {

    @Inject
    ServiceB serviceB;

    public String getName() {
        return "ServiceA";
    }

    public String getServiceBName() {
        return serviceB.getName();
    }
}
