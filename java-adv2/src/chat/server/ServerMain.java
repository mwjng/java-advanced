package chat.server;

import java.io.IOException;

public class ServerMain {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        final SessionManager sessionManager = new SessionManager();

        // CommandManager 점진적으로 변경 예정
//        final CommandManager commandManager = new CommandManagerV1(sessionManager);
//        final CommandManager commandManager = new CommandManagerV2(sessionManager);
//        final CommandManager commandManager = new CommandManagerV3(sessionManager);
        final CommandManager commandManager = new CommandManagerV4(sessionManager);

        final Server server = new Server(PORT, commandManager, sessionManager);
         server.start();
    }
}
