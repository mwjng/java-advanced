package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequest {

    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
        parseBody(reader); // 추가
    }

    // GET /search?q=hello HTTP/1.1
    // Host: localhost:12345

    private void parseRequestLine(final BufferedReader reader) throws IOException {
        final String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IOException("EOF: No request line received");
        }
        final String[] parts = requestLine.split(" ");
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        method = parts[0];
        String[] pathParts = parts[1].split("\\?");
        path = pathParts[0];

        if(pathParts.length > 1) {
            parseQueryParameters(pathParts[1]);
        }
    }
    // q=hello
    // key1=value1&key2=value2

    private void parseQueryParameters(final String queryString) {
        for (String param : queryString.split("&")) {
            final String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], UTF_8) : "";
            queryParameters.put(key, value);
        }
    }
    // Host: localhost:12345
    // Connection: keep-alive
    // Cache-control: max-age=0
    //

    private void parseHeaders(final BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            final String[] headerParts = line.split(":");
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }

    // 추가
    private void parseBody(final BufferedReader reader) throws IOException {
        if(!headers.containsKey("Content-Length")) {
            return;
        }
        final int contentLength = Integer.parseInt(headers.get("Content-Length"));
        final char[] bodyChars = new char[contentLength];
        final int read = reader.read(bodyChars);
        if(read != contentLength) {
            throw new IOException("Fail to read entire body. Expected " + contentLength + " bytes, but read " + read);
        }
        String body = new String(bodyChars);
        log("HTTP Message Body: " + body);

        String contentType = headers.get("Content-Type");
        if ("application/x-www-form-urlencoded".equals(contentType)) {
            // id=id1&name=name1&age=30
            parseQueryParameters(body);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(final String name) {
        return queryParameters.get(name);
    }

    public String getHeader(final String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
            "method='" + method + '\'' +
            ", path='" + path + '\'' +
            ", queryParameters=" + queryParameters +
            ", headers=" + headers +
            '}';
    }
}
