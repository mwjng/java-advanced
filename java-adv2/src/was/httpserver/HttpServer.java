package was.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class HttpServer {

    private final ExecutorService es = Executors.newFixedThreadPool(10);
    private final int port;
    private final ServletManager servletManager;

    public HttpServer(final int port, final ServletManager servletManager) {
        this.port = port;
        this.servletManager = servletManager;
    }

    public void start() throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port: " + port);

        while (true) {
            final Socket socket = serverSocket.accept();
            es.submit(new HttpRequestHandler(socket, servletManager));
        }
    }
}
