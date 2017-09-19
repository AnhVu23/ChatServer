package Server;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;

public class UsersTest {
    private Users userTest1 = new Users("Anh");
    private Users userTest2 = new Users("Long");
    private Users userTest3 = new Users("Anh");
    @Test
    public void getUsersHashSet() throws Exception {
        assertEquals(Users.getUsersHashSet().size(), 0);
        UserNameList.getInstance().insertUser(userTest1);
        UserNameList.getInstance().insertUser(userTest2);
        UserNameList.getInstance().insertUser(userTest3);
        assertEquals(Users.getUsersHashSet().size(), 2);
    }

    @Test
    public void getName() throws Exception {
        assertEquals(userTest1.getName(), "Anh");
        assertEquals(userTest2.getName(), "Long");
    }

    @Test
    public void equals() throws Exception {
        assertEquals(userTest1.equals(userTest2), false);
    }
}