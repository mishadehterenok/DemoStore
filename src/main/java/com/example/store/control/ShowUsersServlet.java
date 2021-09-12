package com.example.store.control;

import com.example.store.dao.UserDao;
import com.example.store.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users", name = "users")
public class ShowUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = UserDao.getInstance().findAllUsers();
        req.setAttribute("users", allUsers);
        System.out.println(allUsers);
        getServletContext().getRequestDispatcher("/res/store/all-users.jsp").forward(req,resp);
    }

}
