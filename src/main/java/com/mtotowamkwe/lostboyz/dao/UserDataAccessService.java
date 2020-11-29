package com.mtotowamkwe.lostboyz.dao;

import com.mtotowamkwe.lostboyz.exception.UserDeletionFailedException;
import com.mtotowamkwe.lostboyz.exception.UserNotFoundException;
import com.mtotowamkwe.lostboyz.exception.UserRegistrationFailedException;
import com.mtotowamkwe.lostboyz.exception.UserUpdateFailedException;
import com.mtotowamkwe.lostboyz.model.User;
import com.mtotowamkwe.lostboyz.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDataAccessService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> registerUser(UUID id, User user) {
        Optional<User> createdUser = null;
        try {
            int rowsAffected = jdbcTemplate.update(
                    Constants.REGISTER_A_USER,
                    id,
                    user.getEmail()
            );
            if (rowsAffected > 0) {
                createdUser = selectUser(id);
            }
        } catch (DataAccessException | UserNotFoundException ex) {
            log.error("registerUser()", ex);
            throw new UserRegistrationFailedException(user);
        }
        return createdUser;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = null;
        try {
            users = jdbcTemplate.query(
                    Constants.SELECT_ALL_USERS,
                    (resultSet, index) -> {
                        UUID id = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String phone_number = resultSet.getString("phone_number");
                        String sign_up_code = resultSet.getString("sign_up_code");

                        User user = new User(id, email);

                        user.setName(name);
                        user.setPhoneNumber(phone_number);
                        user.setSignUpCode(sign_up_code);

                        return  user;
                    });
        } catch (DataAccessException dae) {
            log.error("selectAllUsers()", dae);
        }
        return users;
    }

    @Override
    public Optional<User> selectUser(UUID id) {
        Optional<User> u = null;
        try {
            u = jdbcTemplate.queryForObject(
                    Constants.SELECT_A_USER,
                    new Object[]{id},
                    (resultSet, index) -> {
                        UUID userId = UUID.fromString(resultSet.getString("id"));
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String phone_number = resultSet.getString("phone_number");
                        String sign_up_code = resultSet.getString("sign_up_code");

                        User user = new User(userId, email);

                        user.setName(name);
                        user.setPhoneNumber(phone_number);
                        user.setSignUpCode(sign_up_code);

                        return Optional.of(user);
                    });
        } catch (DataAccessException dae) {
            log.error("selectUser()", dae);
            throw new UserNotFoundException(id);
        }
        return u;
    }

    @Override
    public Optional<User> deleteUser(UUID id) {
        Optional<User> user = null;

        try {
            user = selectUser(id);
        } catch (UserNotFoundException unfexc) {
            log.error("deleteUser()", unfexc);
            throw new UserDeletionFailedException(id);
        }

        if (!user.isEmpty()) {
            try {
                int rowsAffected = jdbcTemplate.update(Constants.DELETE_A_USER, id);
                if (rowsAffected > 0) {
                    return user;
                }
            } catch (DataAccessException dae) {
                log.error("deleteUser()", dae);
                throw new UserDeletionFailedException(id);
            }
        }
        return null;
    }

    @Override
    public Optional<User> updateUser(UUID id, User user) {
        Optional<User> oldUser = null;

        try {
            oldUser = selectUser(id);
        } catch (UserNotFoundException unfex) {
            log.error("updateUser()", unfex);
            throw new UserUpdateFailedException(id, user);
        }

        if (!oldUser.isEmpty()) {
            try {
                int rowsUpdated = jdbcTemplate.update(
                        Constants.UPDATE_A_USER_NAME_AND_PHONE_NUMBER,
                        user.getName(),
                        user.getPhoneNumber(),
                        oldUser.get().getId()
                );
                if (rowsUpdated > 0) {
                    oldUser.get().setName(user.getName());
                    oldUser.get().setPhoneNumber(user.getPhoneNumber());
                }
                return oldUser;
            } catch (DataAccessException dae) {
                log.error("updateUser()", dae);
                throw new UserUpdateFailedException(id, user);
            }
        }

        return null;
    }
}
