package lewiszlw.dagger.demo;

import dagger.Component;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-14
 */
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(App app);

    void inject(ServiceA serviceA);
}
