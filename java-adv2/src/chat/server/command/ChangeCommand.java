package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

public class ChangeCommand implements Command {

    private final SessionManager sessionManager;

    public ChangeCommand(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(final String[] args, final Session session) {
        final String changeName = args[1];
        sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "로 이름을 변경했습니다.");
        session.setUsername(changeName);
    }
}
