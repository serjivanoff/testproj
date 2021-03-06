package net.javarush.usermanager.dao;
import net.javarush.usermanager.model.User;
import java.util.List;
public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserById(int id);
    List<User> getUsersByName(String name);
    List<User>listUsers();
    List<User>nextPage();
    List<User>prevPage();
}