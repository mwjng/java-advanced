package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        final InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost);

        final InetAddress google = InetAddress.getByName("google.com");
        System.out.println(google);
    }
}
