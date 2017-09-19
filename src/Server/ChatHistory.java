package Server;

import java.util.ArrayList;
import java.util.Observable;

public class ChatHistory extends Observable{
    private ArrayList<ChatMessage> chatMessageList;
    private static ChatHistory ourInstance = new ChatHistory();

    public static ChatHistory getInstance() {
        return ourInstance;
    }

    private ChatHistory() {
    }

    public void insert(ChatMessage message) {
        chatMessageList.add(message);
        setChanged();
        notifyObservers();
    }

    public String getLatestMessage() {
        return chatMessageList.get(chatMessageList.size() - 1).toString();
    }

    @Override
    public String toString() {
        String formattedString = "";
        for(ChatMessage message : chatMessageList) {
            formattedString += message.toString();
        }
        return formattedString;
    }
}
