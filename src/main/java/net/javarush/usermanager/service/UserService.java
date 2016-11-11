package net.javarush.usermanager.service;

import net.javarush.usermanager.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserById(int id);
    List<User> listUsers();
    List<User> getUsersByName(String name);
    List<User>nextPage();
    List<User>prevPage();
}