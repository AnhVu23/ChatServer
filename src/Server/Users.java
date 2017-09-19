package Server;

import java.util.HashSet;

public class Users {
    private String name;
    private static HashSet<Users> usersHashSet = new HashSet<>() ;

    public Users(String name) {
        this.name = name;
    }

    public static HashSet<Users> getUsersHashSet() {
        return usersHashSet;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return name.equals(users.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name + " ";
    }
}
