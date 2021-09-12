package com.example.store.dao;

import com.example.store.connection.DBStoreConnection;
import com.example.store.entity.Product;
import com.example.store.entity.Receipt;
import com.example.store.entity.Role;
import com.example.store.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDao{

    public static final String FIND_RECEIPT_BY_USER_ID_AND_PRODUCT_ID = "SELECT * FROM receipt WHERE user_id = ? AND product_id = ?";
    public static final String UPDATE_RECEIPT_ON_PRODUCT_COUNT = "UPDATE receipt SET count=count + ? WHERE id = ?";
    private static volatile ReceiptDao INSTANCE = null;
    public static final String CREATE_RECEIPT = "INSERT INTO receipt (user_id, product_id, count) VALUES (?,?,?)";
    public static final String FIND_RECEIPT_BY_USER_ID = "SELECT * FROM receipt JOIN user ON receipt.user_id = user.id JOIN product ON receipt.product_id = product.id" +
            " WHERE receipt.user_id=?";


    public ReceiptDao() {
    }

    public static ReceiptDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ReceiptDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReceiptDao();
                }
            }
        }
        return INSTANCE;
    }

    public boolean saveReceipt(int user_id, int product_id, int count) {
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_RECEIPT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,user_id);
            statement.setInt(2,product_id);
            statement.setInt(3,count);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Receipt> findReceiptsByUserId(int user_id) {
        List<Receipt> receipts = new ArrayList<>();
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_RECEIPT_BY_USER_ID);
            statement.setInt(1,user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                receipts.add(new Receipt(resultSet.getInt("receipt.id"),
                        new User(resultSet.getInt("user.id"),
                                resultSet.getString("user.username"),
                                resultSet.getString("user.password"),
                                Role.USER),
                        new Product(resultSet.getInt("product.id"),
                                resultSet.getString("product.name"),
                                resultSet.getString("product.description"),
                                resultSet.getInt("product.price")),
                        resultSet.getInt("receipt.count")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipts;
    }

    public Integer ifReceiptExistsReturnId(int user_id, int product_id){
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_RECEIPT_BY_USER_ID_AND_PRODUCT_ID);
            statement.setInt(1,user_id);
            statement.setInt(2,product_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateReceiptOnProductCount(int receipt_id, int count){
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_RECEIPT_ON_PRODUCT_COUNT);
            statement.setInt(1,count);
            statement.setInt(2,receipt_id);
            if (statement.executeUpdate() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
