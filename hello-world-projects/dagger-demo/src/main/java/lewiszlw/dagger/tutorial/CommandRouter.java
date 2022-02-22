package lewiszlw.dagger.tutorial;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

final public class CommandRouter {
    private final Map<String, Command> commands = new HashMap<>();

    @Inject
    CommandRouter(Command command) {
        commands.put(command.key(), command);
    }
}
