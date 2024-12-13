package was.httpserver;

import java.io.PrintWriter;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpResponse {

    private final PrintWriter writer;
    private int statusCode = 200;
    private final StringBuilder bodyBuilder = new StringBuilder();
    private String contentType = "text/html; charset=utf-8";

    public HttpResponse(final PrintWriter writer) {
        this.writer = writer;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public void writeBody(final String body) {
        bodyBuilder.append(body);
    }

    public void flush() {
        int contentLength = bodyBuilder.toString().getBytes(UTF_8).length;
        writer.println("HTTP/1.1 " + statusCode + " " + getReasonPharse(statusCode));
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + contentLength);
        writer.println();
        writer.println(bodyBuilder);
        writer.flush();
    }

    private String getReasonPharse(final int statusCode) {
        switch (statusCode) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown Status";
        }
    }
}
