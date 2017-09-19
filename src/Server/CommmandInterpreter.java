package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CommmandInterpreter implements Runnable, Observer {
    private ChatHistory ov;
    InputStream inputStream;
    PrintStream printStream;

    public CommmandInterpreter() {
    }

    public CommmandInterpreter(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        ov = ChatHistory.getInstance();
        ov.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == ov) {
            ov.getLatestMessage();
        }
    }

    @Override
    public void run() {
        printStream.println("Please put the command in the command list: ");
        Scanner scanner = new Scanner(inputStream);
        boolean quit = false;
        Users clientUser = null;
        while(!quit) {
            String command = scanner.nextLine();
            if(command.charAt(0) != ':') {
                if(clientUser != null) {
                    ChatMessage newMessage = new ChatMessage(command, clientUser);
                    ChatHistory.getInstance().insert(newMessage);
                }
                else {
                    printStream.println("Username not set. Give :user command");
                }
            }
            else {
                StringTokenizer stringTokenizer = new StringTokenizer(command.toLowerCase(), " ");
                String firstToken = stringTokenizer.nextToken();
                switch (firstToken) {
                    case ":users":
                        if(!stringTokenizer.hasMoreTokens()) {
                            printStream.println("Users:");
                            for (Users user : Users.getUsersHashSet()) {
                                printStream.print(user.toString());
                            }
                        }
                        else {
                            String secondToken = stringTokenizer.nextToken();
                            clientUser = new Users(secondToken);
                            System.out.println("Username is " + clientUser.toString());
                            UserNameList.getInstance().insertUser(clientUser);
                        }
                        break;
                    case ":messages":
                        printStream.println("History:");
                        break;
                    case ":quit":
                        printStream.println("Quit");
                        quit = true;
                        break;
                    default:
                        printStream.println("Did not get it!");
                        break;
                }
            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
