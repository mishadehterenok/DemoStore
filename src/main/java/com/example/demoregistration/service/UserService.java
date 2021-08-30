package com.example.demoregistration.service;

import com.example.demoregistration.dao.UserDao;
import com.example.demoregistration.entity.User;

public class UserService {

    private static volatile UserService INSTANCE = null;

    public UserService() {
    }

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public User createUser(User user){
        return UserDao.getInstance().add(user);
    }

    public User findUser(String login, String password){
        return UserDao.getInstance().findUser(login,password);
    }
}
