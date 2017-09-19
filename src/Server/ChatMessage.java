package Server;

import java.sql.Timestamp;

public class ChatMessage {
    private String message;
    private Users user;

    public ChatMessage(String message, Users user) {
        this.message = message;
        this.user = user;
    }

    public String toString() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return message + "from" + " @" + user.getName() + " " + timestamp.getTime() +"\n";
    }


}
