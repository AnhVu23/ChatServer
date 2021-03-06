package Server;

import java.util.ArrayList;
import java.util.Observable;

public class ChatHistory implements ChatObservable{
    private static ArrayList<ChatMessage> chatMessageList = new ArrayList<>();
    private static ChatHistory ourInstance = new ChatHistory();
    private ArrayList<ChatObserver> observerList = new ArrayList<>();

    public static ChatHistory getInstance() {
        return ourInstance;
    }

    private ChatHistory() {
    }

    public void insert(ChatMessage message) {
        chatMessageList.add(message);
        ourInstance.notify(observerList);
    }

    public String getLatestMessage() {
        return chatMessageList.get(chatMessageList.size() - 1).toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(ChatMessage message : chatMessageList) {
            stringBuilder.append(message.toString());
        }
        return stringBuilder.toString();
    }

    public static ArrayList<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    @Override
    public void register(ChatObserver chatObserver) {
        observerList.add(chatObserver);
    }

    @Override
    public void remove(ChatObserver chatObserver) {
        observerList.remove(chatObserver);
    }

    @Override
    public void notify(ArrayList<ChatObserver> observerList) {
        for(ChatObserver chatObserver : observerList) {
            chatObserver.update();
        }
    }


}
