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
public final class ServiceA_MembersInjector implements MembersInjector<ServiceA> {
  private final Provider<ServiceB> serviceBProvider;

  public ServiceA_MembersInjector(Provider<ServiceB> serviceBProvider) {
    this.serviceBProvider = serviceBProvider;
  }

  public static MembersInjector<ServiceA> create(Provider<ServiceB> serviceBProvider) {
    return new ServiceA_MembersInjector(serviceBProvider);}

  @Override
  public void injectMembers(ServiceA instance) {
    injectServiceB(instance, serviceBProvider.get());
  }

  public static void injectServiceB(ServiceA instance, ServiceB serviceB) {
    instance.serviceB = serviceB;
  }
}
