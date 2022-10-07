import java.util.Scanner;

public class SocketManager {
    public static void main(String[] args) {

        // First get port for this server.

        System.out.println("Enter port for this server to listen on: ");
        int iPort = new Scanner(System.in).nextInt();

        // Next get IP and port of server for client to connect to
        System.out.println("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();
        System.out.println("Enter port of the server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        // Then start up server thread
        SocketServer oServer = new SocketServer(iPort);
        Thread oServerThread = new Thread(oServer);
        oServerThread.start();

        while(true){
            SocketClient oClient = new SocketClient();
            System.out.print("Enter message to send: ");
            String sMessage = new Scanner(System.in).nextLine();

            String sReturnedMessage = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);

            System.out.println("[client] returned message:" + sReturnedMessage);
        }
    }
}
