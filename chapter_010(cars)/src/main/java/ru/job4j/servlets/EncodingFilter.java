package ru.job4j.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter for correct cyrillic text view.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */

public class EncodingFilter implements Filter {

    private String defaultEncoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (encoding != null) {
            defaultEncoding = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(defaultEncoding);
        resp.setContentType(String.format("text/json;charset=%s", defaultEncoding));
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
