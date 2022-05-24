package folha20;

import java.io.IOException;
import java.net.ServerSocket;

public class PortsInUse {
    public static void main(String[] args) {

        boolean used = false;

        for (int portNumber = 0; portNumber < 65536; portNumber++) {

            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
            ) {

                System.out.println("Porto " + portNumber + ": livre");

            } catch (IOException e) {
                System.out.println("Porto " + portNumber + ": usado");
            }
        }
    }
}
