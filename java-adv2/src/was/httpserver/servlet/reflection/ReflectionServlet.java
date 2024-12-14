package was.httpserver.servlet.reflection;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectionServlet implements HttpServlet {

    private final List<Object> controllers;

    public ReflectionServlet(final List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(final HttpRequest request, final HttpResponse response) throws IOException {
        final String path = request.getPath();

        // SiteControllerV6, SearchControllerV6
        for (Object controller : controllers) {
            final Class<?> aClass = controller.getClass();
            final Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if(path.equals("/" + methodName)) {
                    invoke(controller, method, request, response);
                    return;
                }
            }
        }
        throw new PageNotFoundException("request = " + path);
    }

    private static void invoke(final Object controller, final Method method, final HttpRequest request, final HttpResponse response) {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
