package lewiszlw.dagger.demo;

import dagger.Module;
import dagger.Provides;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-14
 */
@Module
public class MainModule {

    @Provides
    public ServiceA provideServiceA() {
        return new ServiceA();
    }

    @Provides
    public ServiceB provideServiceB() {
        return new ServiceB();
    }
}
