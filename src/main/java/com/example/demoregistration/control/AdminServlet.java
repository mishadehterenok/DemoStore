package com.example.demoregistration.control;

import com.example.demoregistration.dao.UserDao;
import com.example.demoregistration.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserDao.getInstance().findAll();
        req.setAttribute("users",users);
        getServletContext().getRequestDispatcher("/res/admin.jsp").forward(req,resp);
    }
}
