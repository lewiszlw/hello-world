package lewiszlw.dagger.demo;

import javax.inject.Inject;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-13
 */
public class App {

    @Inject
    ServiceA serviceA;

    private ServiceB serviceB;

    public App() {
        MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        mainComponent.inject(this);
        mainComponent.inject(serviceA);
    }
    @Inject
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String getServiceBName() {
        return serviceB.getName();
    }
}
