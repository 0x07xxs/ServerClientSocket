import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {

    private int thisServerPort;// Server port

    /**
     * This constructor forces port to be passed in that is needed for server startup.
     */
    public SocketServer(int iPort) {
        thisServerPort = iPort;
    }

    /**
     * This thread listens for connecting clients, and receives messages.
     */

    public void run() {
        try (ServerSocket oServer = new ServerSocket(thisServerPort)) {

            System.out.println("Server is listening on port" + thisServerPort);

            while (true) { //
                Socket oSocket = oServer.accept(); // Pauses till client connects
                System.out.print("[server]: New client connected: " + oSocket.getRemoteSocketAddress());
                // Prints the IP address of the client

                // Prepare for input
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                // Prepare for output
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Get one time message from client
                String rReceiveMessage = reader.readLine();
                System.out.println("[server]: Server received message: " +rReceiveMessage);

                writer.println("Received your message");
                writer.flush();
            }
        }
        catch (IOException ex) {
            System.out.println("[Server]: Server exception" + ex.getMessage());
            ex.printStackTrace();

        }
    }


}
