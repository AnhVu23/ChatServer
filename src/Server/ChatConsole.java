package Server;

import java.util.Observable;
import java.util.Observer;

public class ChatConsole implements Observer {
    private ChatHistory ov;

    public ChatConsole() {
        ov = ChatHistory.getInstance();
        ov.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(ov == o) {
            System.out.println(ov.getLatestMessage());
        }
    }
}
