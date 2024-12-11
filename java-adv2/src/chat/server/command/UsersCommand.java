package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;
import java.util.List;

public class UsersCommand implements Command {

    private final SessionManager sessionManager;

    public UsersCommand(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(final String[] args, final Session session) throws IOException {
        final List<String> usernames = sessionManager.getAllUsername();
        final StringBuilder sb = new StringBuilder();
        sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
        for (String username : usernames) {
            sb.append(" - ").append(username).append("\n");
        }
        session.send(sb.toString());
    }
}
