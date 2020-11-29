package com.mtotowamkwe.lostboyz.service;

import com.mtotowamkwe.lostboyz.dao.UserDao;
import com.mtotowamkwe.lostboyz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("postgres") UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> addUser(User user) {
        return userDao.registerUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUser(UUID id) {
        return userDao.selectUser(id);
    }

    public Optional<User> deleteUser(UUID id) {
        return userDao.deleteUser(id);
    }

    public Optional<User> updateUser(UUID id, User user) {
        return userDao.updateUser(id, user);
    }

}
