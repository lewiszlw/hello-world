package lewiszlw.dagger.tutorial;

import dagger.internal.Factory;
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
public final class HelloWorldCommand_Factory implements Factory<HelloWorldCommand> {
  private final Provider<Outputter> outputterProvider;

  public HelloWorldCommand_Factory(Provider<Outputter> outputterProvider) {
    this.outputterProvider = outputterProvider;
  }

  @Override
  public HelloWorldCommand get() {
    return new HelloWorldCommand(outputterProvider.get());
  }

  public static HelloWorldCommand_Factory create(Provider<Outputter> outputterProvider) {
    return new HelloWorldCommand_Factory(outputterProvider);
  }

  public static HelloWorldCommand newInstance(Outputter outputter) {
    return new HelloWorldCommand(outputter);
  }
}
