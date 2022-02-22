package lewiszlw.dagger.tutorial.module;

import dagger.Module;
import dagger.Provides;
import lewiszlw.dagger.tutorial.Outputter;

@Module
public abstract class SystemOutModule {
    @Provides
    static Outputter textOutputter() {
        return System.out::println;
    }
}
