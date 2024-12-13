package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class InternalErrorServlet implements HttpServlet {

    @Override
    public void service(final HttpRequest request, final HttpResponse response) throws IOException {
        response.setStatusCode(500);
        response.writeBody("<h1>Internal Error</h1>");
    }
}
