package com.example.store.control;

import com.example.store.dao.UserDao;
import com.example.store.entity.Role;
import com.example.store.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/store-registration", name = "reg")
public class StoreRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/res/store/store-registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean usernameAlreadyUse = UserDao.getInstance().isUsernameAlreadyUse(username);
        if (usernameAlreadyUse){
            resp.sendRedirect("/store-registration");
        } else {
            User user = new User(username, password, Role.USER);
            int user_id = UserDao.getInstance().saveUser(user);
            user.setId(user_id);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/store-products");
        }
    }
}
