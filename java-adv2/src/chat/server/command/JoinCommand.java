package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

public class JoinCommand implements Command {

    private final SessionManager sessionManager;

    public JoinCommand(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(final String[] args, final Session session) {
        final String username = args[1];
        session.setUsername(username);
        sessionManager.sendAll(username + "님이 입장했습니다.");
    }
}
