package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 12345);
        final InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000);
            final int read = input.read();
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
