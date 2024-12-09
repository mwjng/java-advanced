package network.exception.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMainV2 {

    public static void main(String[] args) throws IOException {
        final long start = System.currentTimeMillis();

        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        final long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));
    }
}
