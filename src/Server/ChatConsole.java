package Server;

import java.util.Observable;
import java.util.Observer;

public class ChatConsole implements ChatObserver {
    private ChatHistory ov;

    public ChatConsole() {
        ov = ChatHistory.getInstance();
        ov.register(this);
    }


    @Override
    public void update() {
        System.out.println(ov.getLatestMessage());
    }
}
