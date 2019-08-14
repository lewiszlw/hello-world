package lewiszlw.dagger.demo;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainModule_ProvideServiceBFactory implements Factory<ServiceB> {
  private final MainModule module;

  public MainModule_ProvideServiceBFactory(MainModule module) {
    this.module = module;
  }

  @Override
  public ServiceB get() {
    return provideServiceB(module);
  }

  public static MainModule_ProvideServiceBFactory create(MainModule module) {
    return new MainModule_ProvideServiceBFactory(module);
  }

  public static ServiceB provideServiceB(MainModule instance) {
    return Preconditions.checkNotNull(instance.provideServiceB(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
