package Server;

import java.sql.Timestamp;

public class ChatMessage {
    private String message;
    private Users user;
    private Timestamp timestamp;

    public ChatMessage(String message, Users user) {
        this.message = message;
        this.user = user;
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String toString() {
        return message + " from" + " @" + user.getName() + " " + timestamp.getTime() +"\n";
    }


}
