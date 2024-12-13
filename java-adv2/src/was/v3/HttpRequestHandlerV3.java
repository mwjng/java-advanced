package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(final Socket socket) {
        this.socket = socket;
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
             final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)
        ) {
            String requestString = requestToString(br);
            // GET /favicon.ico HTTP/1.1
            if (requestString.contains("/favicon.ic")) {
                log("favicon 요청");
                return;
            }
            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 생성 중...");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }
            log("HTTP 응답 전달 완료");
        }
    }

    private void home(final PrintWriter writer) {
        // 원칙적으로 Content-Length 를 계산해서 전달해야 하지만, 예제를 단순하게 설명하기 위해 생략하겠다.
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>home</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>검색</a></li>");
        writer.println("</ul>");
        writer.flush();
    }

    private void site1(final PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site1</h1>");
        writer.flush();
    }

    private void site2(final PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>site2</h1>");
        writer.flush();
    }

    private void notFound(final PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        writer.flush();
    }

    // "/search?q=hello"
    // GET /search?q=hello HTTP/1.1
    private void search(final PrintWriter writer, final String requestString) {
        final int startIndex = requestString.indexOf("q=");
        final int endIndex = requestString.indexOf(" ", startIndex + 2);
        final String query = requestString.substring(startIndex + 2, endIndex);
        final String decode = URLDecoder.decode(query, UTF_8);

        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println();
        writer.println("<h1>search</h1>");
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>");
        writer.println("<li>decode: " + decode + "</li>");
        writer.println("</ul>");
        writer.flush();
    }

    private static String requestToString(final BufferedReader br) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
