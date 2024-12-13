package was.v5.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class SearchServlet implements HttpServlet {

    @Override
    public void service(final HttpRequest request, final HttpResponse response) {
        final String query = request.getParameter("q");
        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }
}
