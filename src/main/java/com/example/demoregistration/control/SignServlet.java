package com.example.demoregistration.control;

import com.example.demoregistration.entity.Role;
import com.example.demoregistration.entity.User;
import com.example.demoregistration.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign")
public class SignServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(SignServlet.class);
    private static final Logger ROOT_LOGGER = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/res/sign.jsp").forward(req, resp);

        LOGGER.log(Level.INFO,"warn-error");
        ROOT_LOGGER.log(Level.INFO,"root_error");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserService.getInstance().findUser(login, password);
        if (user != null){
            req.getSession().setAttribute("user",user);
            if (user.getRole().equals(Role.ADMIN)){
                resp.sendRedirect("/admin");
            } else if (user.getRole().equals(Role.USER)){
                resp.sendRedirect("/profile");
            }
        } else {
            resp.sendRedirect("/sign");
        }
    }
}
