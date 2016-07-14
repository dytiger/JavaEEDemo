package org.forten.sample.jee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/7/14.
 */
@WebServlet(urlPatterns = "/user/hello.do")
public class HelloUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // request.getParameter(para)可以从请求中获取某个请求参数的值
        // 这个方法的参数是一个String，它对应前端页面中input等控件的name取值
        String name = request.getParameter("xm");
        PrintWriter out = response.getWriter();
        out.print("<h1 style='color:red;'>你好"+name+"!</h1>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // request.getParameter(para)可以从请求中获取某个请求参数的值
        // 这个方法的参数是一个String，它对应前端页面中input等控件的name取值
        String name = request.getParameter("xm");
        PrintWriter out = response.getWriter();
        out.print("<h1>你好"+name+"!</h1>");
        out.close();
    }
}
