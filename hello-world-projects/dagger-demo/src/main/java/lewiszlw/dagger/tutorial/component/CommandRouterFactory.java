package lewiszlw.dagger.tutorial.component;

import dagger.Component;
import lewiszlw.dagger.tutorial.CommandRouter;
import lewiszlw.dagger.tutorial.module.HelloWorldModule;
import lewiszlw.dagger.tutorial.module.SystemOutModule;

@Component(modules = {HelloWorldModule.class, SystemOutModule.class})
public interface CommandRouterFactory {
    CommandRouter router();
}
