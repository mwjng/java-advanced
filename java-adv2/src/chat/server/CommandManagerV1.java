package chat.server;

import java.io.IOException;

public class CommandManagerV1 implements CommandManager {

    private final SessionManager sessionManager;

    public CommandManagerV1(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(final String totalMessage, final Session session) throws IOException {
        if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        }
        sessionManager.sendAll(totalMessage);
    }
}
