package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CommmandInterpreter implements Runnable, ChatObserver {
    private ChatHistory ov;
    InputStream inputStream;
    PrintStream printStream;

    public CommmandInterpreter() {
    }

    public CommmandInterpreter(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
        ov = ChatHistory.getInstance();
        ov.register(this);
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
                    ChatConsole chatConsole = new ChatConsole();
                    chatConsole.update();
                }
                else {
                    printStream.println("Username not set. Give :user command");
                }
            }
            else {
                StringTokenizer stringTokenizer = new StringTokenizer(command, " ");
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
                            if(!UserNameList.getInstance().checkExistingUser(clientUser)) {
                                printStream.println("Username is " + clientUser.toString());
                                UserNameList.getInstance().insertUser(clientUser);
                            }
                            else {
                                printStream.println("Username already exist");
                            }
                        }
                        break;
                    case ":messages":
                        printStream.println(ChatHistory.getInstance().toString());
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
        printStream.println("Good bye!");
        printStream.println("Connection closed by foreign host");
    }


    @Override
    public void update() {
        printStream.println(ChatHistory.getInstance().getLatestMessage());
    }
}
