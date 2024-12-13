package was.v2;

import java.io.IOException;

public class ServerMainV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        final HttpServerV2 server = new HttpServerV2(12345);
        server.start();
    }
}
