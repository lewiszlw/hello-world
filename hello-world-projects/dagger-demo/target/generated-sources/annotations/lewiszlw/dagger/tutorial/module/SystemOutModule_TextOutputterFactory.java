package lewiszlw.dagger.tutorial.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import lewiszlw.dagger.tutorial.Outputter;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SystemOutModule_TextOutputterFactory implements Factory<Outputter> {
  private static final SystemOutModule_TextOutputterFactory INSTANCE = new SystemOutModule_TextOutputterFactory();

  @Override
  public Outputter get() {
    return textOutputter();
  }

  public static SystemOutModule_TextOutputterFactory create() {
    return INSTANCE;
  }

  public static Outputter textOutputter() {
    return Preconditions.checkNotNull(SystemOutModule.textOutputter(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
