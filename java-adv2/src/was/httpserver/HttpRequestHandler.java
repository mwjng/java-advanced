package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandler implements Runnable {

    private final Socket socket;
    private final ServletManager servletManager;

    public HttpRequestHandler(final Socket socket, final ServletManager servletManager) {
        this.socket = socket;
        this.servletManager = servletManager;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (socket;
             final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)
        ) {
            final HttpRequest request = new HttpRequest(reader);
            final HttpResponse response = new HttpResponse(writer);

            log("HTTP 요청: " + request);
            servletManager.execute(request, response);
            response.flush();
            log("HTTP 응답 완료");
        }
    }
}
