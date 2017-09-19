package Server;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChatHistoryTest {
    ChatHistory chatHistory = ChatHistory.getInstance();
    @Test
    public void insert() throws Exception {
        ChatMessage chatMessage = new ChatMessage("First message", new Users("Anh"));
        chatHistory.insert(chatMessage);
        assertEquals(true, ChatHistory.getChatMessageList().contains(chatMessage));
    }

    @Test
    public void getLatestMessage() throws Exception {
        chatHistory.insert(new ChatMessage("First message", new Users("Anh")));
        chatHistory.insert(new ChatMessage("Second message", new Users("Anh")));
        assertEquals(chatHistory.getLatestMessage().toString(), new ChatMessage("Second message", new Users("Anh")).toString());
    }

}