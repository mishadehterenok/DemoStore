package com.example.store.dao;

import com.example.store.connection.DBStoreConnection;
import com.example.store.entity.Role;
import com.example.store.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static volatile UserDao INSTANCE = null;
    public static final String CREATE_USER = "INSERT INTO user (username, password, role_id) VALUES (?,?,?);";
    public static final String FIND_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?;";
    public static final String FIND_USER_BY_USERNAME = "SELECT * FROM user WHERE username=?;";
    public static final String FIND_ALL_USERS = "SELECT * FROM user;";


    public UserDao() {
    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }

    public Integer saveUser(User user) {
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setInt(3,2);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()){
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByUsernameAndPassword(String username, String password) {
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME_AND_PASSWORD);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("role_id") == 1) {
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("password"),
                            resultSet.getString("username"), Role.ADMIN);
                } else if (resultSet.getInt("role_id") == 2){
                    return new User(resultSet.getInt("id"),
                            resultSet.getString("password"),
                            resultSet.getString("username"), Role.USER);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUsernameAlreadyUse(String username) {
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("role_id") == 1) {
                    users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                            resultSet.getString("password"), Role.ADMIN));
                } else if (resultSet.getInt("role_id") == 2) {
                    users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                            resultSet.getString("password"), Role.USER));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
