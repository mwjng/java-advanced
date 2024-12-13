package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class DiscardServlet implements HttpServlet {

    @Override
    public void service(final HttpRequest request, final HttpResponse response) throws IOException {
        // empty
    }
}
