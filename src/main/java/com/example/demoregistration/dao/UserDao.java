package com.example.demoregistration.dao;

import com.example.demoregistration.dbconnection.DBConnection;
import com.example.demoregistration.entity.Role;
import com.example.demoregistration.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements BaseDao<User> {

    public static final String FIND_USER = "SELECT * FROM users WHERE login=? AND password=?";
    public static final String FIND_ALL = "SELECT * FROM users";

    public UserDao() {
    }

    public static final String CREATE_USER = "INSERT INTO users (login, password, role_id) VALUES (?,?,?)";
    private static volatile UserDao INSTANCE = null;

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

    @Override
    public User add(User user) {
        int id = 0;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setInt(3,2);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()){
                id = keys.getInt(1);
            }
            user.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUser(String login, String password){
        User user = null;
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER);
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = null;
                if (resultSet.getInt(4) == 1) {
                    role = Role.ADMIN;
                } else if (resultSet.getInt(4) == 2){
                    role = Role.USER;
                }
                user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), Role.USER));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
