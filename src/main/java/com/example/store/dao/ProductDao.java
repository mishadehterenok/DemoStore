package com.example.store.dao;

import com.example.store.connection.DBStoreConnection;
import com.example.store.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {

    private static volatile ProductDao INSTANCE = null;
    public static final String FIND_ALL = "SELECT * FROM product";

//    public static final String CREATE_PRODUCT = "INSERT INTO product (name, description, price) VALUES (?,?,?)";
//    public static final String FIND_PRODUCT = "SELECT * FROM product WHERE name=?";


    public ProductDao() {
    }

    public static ProductDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ProductDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductDao();
                }
            }
        }
        return INSTANCE;
    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = DBStoreConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

//    public Product save(Product product) {
//        int id = 0;
//        try(Connection connection = DBStoreConnection.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1,product.getName());
//            statement.setString(2,product.getDescription());
//            statement.setInt(3,product.getPrice());
//            statement.executeUpdate();
//            ResultSet keys = statement.getGeneratedKeys();
//            if (keys.next()){
//                id = keys.getInt(1);
//            }
//            product.setId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return product;
//    }

//    public Product find(String name) {
//        try(Connection connection = DBStoreConnection.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement(FIND_PRODUCT);
//            statement.setString(1,name);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Product(resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getInt("price"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
