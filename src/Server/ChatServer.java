package Server;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public ChatServer() {
    }

    public void serve() {
        try { //Create a fix server socket
            ServerSocket serverSocket = new ServerSocket(44444);
            while(true) {
                System.out.println("The port which server is listening is " + serverSocket.getLocalPort());
                Socket socket = serverSocket.accept();
                System.out.println("Connection is accepted");
                CommmandInterpreter ci = new CommmandInterpreter(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.println("Accepted");
                Thread thread = new Thread(ci);
                thread.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
