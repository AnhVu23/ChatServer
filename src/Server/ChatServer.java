package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public ChatServer() {
    }

    public void serve() {
        try {
            ServerSocket serverSocket = new ServerSocket(0, 2);
            System.out.println("The port which server is listening is " + serverSocket.getLocalPort());
            Socket socket = serverSocket.accept();
            System.out.println("Connection is accepted");
            CommmandInterpreter ci = new CommmandInterpreter(System.in, System.out);
            Thread thread = new Thread(ci);
            thread.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
