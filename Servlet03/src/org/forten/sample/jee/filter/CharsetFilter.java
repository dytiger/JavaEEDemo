package org.forten.sample.jee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/21.
 */
//@WebFilter(urlPatterns = "/*")
public class CharsetFilter implements Filter {
    private String charset = null;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
    }

}
