package com.example.store.filters;

import com.example.store.entity.Role;
import com.example.store.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "users")
public class BlockerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equals(Role.USER)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/receipts");
            } else if (user.getRole().equals(Role.ADMIN)){
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }  else {
            ((HttpServletResponse) servletResponse).sendRedirect("/store");
        }
    }
}
