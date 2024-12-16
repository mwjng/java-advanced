package was.v8;

import was.httpserver.HttpServer;
import was.httpserver.HttpServlet;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.annotation.AnnotationServletV2;
import was.httpserver.servlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class ServerMainV8 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        final List<Object> controllers = List.of(new SiteControllerV8(), new SearchControllerV8());
//        final HttpServlet annotationServlet = new AnnotationServletV2(controllers);
        final HttpServlet annotationServlet = new AnnotationServletV3(controllers);

        final ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(annotationServlet);
        servletManager.add("/favicon.ico", new DiscardServlet());

        final HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
