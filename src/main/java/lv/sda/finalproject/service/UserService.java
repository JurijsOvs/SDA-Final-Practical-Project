package lv.sda.finalproject.service;


import lv.sda.finalproject.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

