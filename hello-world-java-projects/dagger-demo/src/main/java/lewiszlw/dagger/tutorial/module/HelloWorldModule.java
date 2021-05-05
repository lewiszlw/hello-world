package lewiszlw.dagger.tutorial.module;

import dagger.Binds;
import dagger.Module;
import lewiszlw.dagger.tutorial.Command;
import lewiszlw.dagger.tutorial.HelloWorldCommand;

@Module
public abstract class HelloWorldModule {
    @Binds
    abstract Command helloWorldCommand(HelloWorldCommand command);
}
