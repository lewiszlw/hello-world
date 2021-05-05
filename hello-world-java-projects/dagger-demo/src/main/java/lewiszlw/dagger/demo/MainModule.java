package lewiszlw.dagger.demo;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-14
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    public ServiceA provideServiceA() {
        return new ServiceA();
    }

    @Provides
    @Singleton
    public ServiceB provideServiceB() {
        return new ServiceB();
    }
}
