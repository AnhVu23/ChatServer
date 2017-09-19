package Server;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserNameListTest {
    private UserNameList userNameList = UserNameList.getInstance();
    private Users userTest1 = new Users("Anh");
    private Users userTest2 = new Users("Long");
    @Test
    public void checkExistingUser() throws Exception {
        userNameList.insertUser(userTest1);
        userNameList.insertUser(userTest2);
        assertEquals(userNameList.checkExistingUser(new Users("Anh")), true);
        assertEquals(userNameList.checkExistingUser(new Users("Dat")), false);
    }

    @Test
    public void insertUser() throws Exception {
        userNameList.insertUser(new Users("Dat"));
        userNameList.insertUser(new Users("Anh"));
        assertEquals(userNameList.checkExistingUser(new Users("Dat")), true);
    }

    @Test
    public void removeUser() throws Exception {
        assertEquals(userNameList.checkExistingUser(new Users("Dat")),true);
        userNameList.removeUser(new Users("Dat"));
        assertEquals(userNameList.checkExistingUser(new Users("Dat")), false);
    }

}