package Server;

public class UserNameList {
    private static UserNameList ourInstance = new UserNameList();

    public static UserNameList getInstance() {
        return ourInstance;
    }

    private UserNameList() {
    }


    public boolean checkExistingUser(Users userCheck) {
        for(Users user : Users.getUsersHashSet()) {
            if(userCheck.equals(user)) {
                return true;
            }
        }
        return false;
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
