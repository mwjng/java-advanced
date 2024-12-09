package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            final DataInputStream input = new DataInputStream(socket.getInputStream());
            final DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // 클라이언트로부터 문자 받기
                final String received = input.readUTF();
                log("client -> server: " + received);

                if(received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                final String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server " + toSend);
            }

            // 자원 정리
            log("연결 종료: " + socket);
            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
