package com.mtotowamkwe.lostboyz.utils;

public class Constants {
    // sql
    public static final String SELECT_ALL_USERS = "SELECT id, email, name, phone_number, sign_up_code FROM users";
    public static final String SELECT_A_USER = "SELECT id, email, name, phone_number, sign_up_code FROM users WHERE id = ?";
    public static final String REGISTER_A_USER = "INSERT INTO users (id, email) VALUES (?, ?)";
    public static final String DELETE_A_USER = "DELETE FROM users WHERE id = ?";
    public static final String UPDATE_A_USER_NAME_AND_PHONE_NUMBER = "UPDATE users SET name = ?, phone_number = ? WHERE id = ?";

    // exceptions
    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "Could not find the user ";
    public static final String USER_UPDATE_FAILED_ERROR_MESSAGE = "Could not update the user ";
    public static final String USER_REGISTRATION_ERROR_MESSAGE = "Could not create the user ";
    public static final String USER_DELETION_FAILED_ERROR_MESSAGE = "Could not delete the user ";

    // paths
    public static final String API_USERS_ENDPOINT = "/api/v1/users";
    public static final String API_ID_ENDPOINT =  API_USERS_ENDPOINT + "/{id}";
    public static final String PATH_VARIABLE = "id";
}
