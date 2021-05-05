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
public final class CommandRouter_Factory implements Factory<CommandRouter> {
  private final Provider<Command> commandProvider;

  public CommandRouter_Factory(Provider<Command> commandProvider) {
    this.commandProvider = commandProvider;
  }

  @Override
  public CommandRouter get() {
    return new CommandRouter(commandProvider.get());
  }

  public static CommandRouter_Factory create(Provider<Command> commandProvider) {
    return new CommandRouter_Factory(commandProvider);
  }

  public static CommandRouter newInstance(Command command) {
    return new CommandRouter(command);
  }
}
