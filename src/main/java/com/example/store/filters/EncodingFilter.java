package com.example.store.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
