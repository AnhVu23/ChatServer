package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
                switch (firstToken) { //Check the first word
                    case ":users":
                        if(!stringTokenizer.hasMoreTokens()) {
                            printStream.print("Users:" + "\n");
                            printStream.print(UserNameList.getInstance().toString());
                        }
                        else { //If it is users, check the second word
                            String secondToken = stringTokenizer.nextToken();
                            if(!UserNameList.getInstance().checkExistingUser(new Users(secondToken))) {
                                clientUser = new Users(secondToken);
                                printStream.print("Username is " + clientUser.toString());
                                UserNameList.getInstance().insertUser(clientUser);
                            }
                            else {
                                printStream.println("Username already exist");
                                clientUser = UserNameList.getInstance().getUser(secondToken);

                            }
                        }
                        break;
                    case ":messages":
                        printStream.print(ChatHistory.getInstance().toString());
                        break;
                    case ":quit": //End of Command Interpreter
                        printStream.println("Quit");
                        printStream.println("Good bye!");
                        printStream.println("Connection closed by foreign host");
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
        printStream.print(ChatHistory.getInstance().getLatestMessage());
    }
}
