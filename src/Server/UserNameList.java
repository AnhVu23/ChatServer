package Server;

public class UserNameList {
    private static UserNameList ourInstance = new UserNameList();

    public static UserNameList getInstance() {
        return ourInstance;
    }

    private UserNameList() {
    }


    public boolean checkExistingUser(Users userCheck) {
        return Users.getUsersHashSet().contains(userCheck);
    }

    public void insertUser(Users user) {
        if(this.checkExistingUser(user) == false) {
            Users.getUsersHashSet().add(user);
        }
    }

    public void removeUser(Users user) {
        if(this.checkExistingUser(user)) {
            Users.getUsersHashSet().remove(user);
        }
    }
}
