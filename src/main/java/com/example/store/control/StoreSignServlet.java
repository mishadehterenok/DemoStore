package com.example.store.control;

import com.example.store.entity.Role;
import com.example.store.dao.UserDao;
import com.example.store.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/store-sign")
public class StoreSignServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/res/store/store-sign-in.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = UserDao.getInstance().findByUsernameAndPassword(username, password);
        if (user != null){
            req.getSession().setAttribute("user",user);
            if (user.getRole().equals(Role.ADMIN)){
                resp.sendRedirect("/users");
            } else if (user.getRole().equals(Role.USER)){
                resp.sendRedirect("/store-products");
            }
        } else {
            resp.sendRedirect("/store-sign");
        }
    }
}
