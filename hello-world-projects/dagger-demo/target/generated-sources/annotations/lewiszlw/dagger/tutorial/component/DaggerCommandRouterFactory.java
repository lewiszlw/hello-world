package lewiszlw.dagger.tutorial.component;

import javax.annotation.Generated;
import lewiszlw.dagger.tutorial.CommandRouter;
import lewiszlw.dagger.tutorial.CommandRouter_Factory;
import lewiszlw.dagger.tutorial.HelloWorldCommand;
import lewiszlw.dagger.tutorial.HelloWorldCommand_Factory;
import lewiszlw.dagger.tutorial.module.SystemOutModule_TextOutputterFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerCommandRouterFactory implements CommandRouterFactory {
  private DaggerCommandRouterFactory() {

  }

  public static Builder builder() {
    return new Builder();
  }

  public static CommandRouterFactory create() {
    return new Builder().build();
  }

  private HelloWorldCommand getHelloWorldCommand() {
    return HelloWorldCommand_Factory.newInstance(SystemOutModule_TextOutputterFactory.textOutputter());}

  @Override
  public CommandRouter router() {
    return CommandRouter_Factory.newInstance(getHelloWorldCommand());}

  public static final class Builder {
    private Builder() {
    }

    public CommandRouterFactory build() {
      return new DaggerCommandRouterFactory();
    }
  }
}
