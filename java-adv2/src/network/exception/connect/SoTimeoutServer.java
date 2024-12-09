package network.exception.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoTimeoutServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        final ServerSocket serverSocket = new ServerSocket(12345);
        final Socket socket = serverSocket.accept();

        Thread.sleep(10000000);
    }
}
