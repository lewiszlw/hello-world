package lewiszlw.dagger.demo;

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
public final class DaggerMainComponent implements MainComponent {
  private final MainModule mainModule;

  private DaggerMainComponent(MainModule mainModuleParam) {
    this.mainModule = mainModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static MainComponent create() {
    return new Builder().build();
  }

  @Override
  public void inject(App app) {
    injectApp(app);}

  @Override
  public void inject(ServiceA serviceA) {
    injectServiceA(serviceA);}

  private App injectApp(App instance) {
    App_MembersInjector.injectServiceA(instance, MainModule_ProvideServiceAFactory.provideServiceA(mainModule));
    App_MembersInjector.injectSetServiceB(instance, MainModule_ProvideServiceBFactory.provideServiceB(mainModule));
    return instance;
  }

  private ServiceA injectServiceA(ServiceA instance) {
    ServiceA_MembersInjector.injectServiceB(instance, MainModule_ProvideServiceBFactory.provideServiceB(mainModule));
    return instance;
  }

  public static final class Builder {
    private MainModule mainModule;

    private Builder() {
    }

    public Builder mainModule(MainModule mainModule) {
      this.mainModule = Preconditions.checkNotNull(mainModule);
      return this;
    }

    public MainComponent build() {
      if (mainModule == null) {
        this.mainModule = new MainModule();
      }
      return new DaggerMainComponent(mainModule);
    }
  }
}
