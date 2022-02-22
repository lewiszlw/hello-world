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
public final class MainModule_ProvideServiceAFactory implements Factory<ServiceA> {
  private final MainModule module;

  public MainModule_ProvideServiceAFactory(MainModule module) {
    this.module = module;
  }

  @Override
  public ServiceA get() {
    return provideServiceA(module);
  }

  public static MainModule_ProvideServiceAFactory create(MainModule module) {
    return new MainModule_ProvideServiceAFactory(module);
  }

  public static ServiceA provideServiceA(MainModule instance) {
    return Preconditions.checkNotNull(instance.provideServiceA(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
