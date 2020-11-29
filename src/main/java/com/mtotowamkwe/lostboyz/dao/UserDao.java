package com.mtotowamkwe.lostboyz.dao;

import com.mtotowamkwe.lostboyz.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    default Optional<User> registerUser(User user) {
        UUID id = UUID.randomUUID();
        return registerUser(id, user);
    }

    Optional<User> registerUser(UUID id, User user);

    List<User> selectAllUsers();

    Optional<User> selectUser(UUID id);

    Optional<User> deleteUser(UUID id);

    Optional<User> updateUser(UUID id, User user);
}
