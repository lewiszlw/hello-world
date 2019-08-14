package lewiszlw.dagger.demo;

import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class App_MembersInjector implements MembersInjector<App> {
  private final Provider<ServiceA> serviceAProvider;

  private final Provider<ServiceB> serviceBProvider;

  public App_MembersInjector(Provider<ServiceA> serviceAProvider,
      Provider<ServiceB> serviceBProvider) {
    this.serviceAProvider = serviceAProvider;
    this.serviceBProvider = serviceBProvider;
  }

  public static MembersInjector<App> create(Provider<ServiceA> serviceAProvider,
      Provider<ServiceB> serviceBProvider) {
    return new App_MembersInjector(serviceAProvider, serviceBProvider);}

  @Override
  public void injectMembers(App instance) {
    injectServiceA(instance, serviceAProvider.get());
    injectSetServiceB(instance, serviceBProvider.get());
  }

  public static void injectServiceA(App instance, ServiceA serviceA) {
    instance.serviceA = serviceA;
  }

  public static void injectSetServiceB(App instance, ServiceB serviceB) {
    instance.setServiceB(serviceB);
  }
}
