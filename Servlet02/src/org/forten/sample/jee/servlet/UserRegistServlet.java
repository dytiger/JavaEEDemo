package org.forten.sample.jee.servlet;

import org.forten.sample.jee.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/14.
 */
@WebServlet(urlPatterns = "/user/regist.do")
public class UserRegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String email = request.getParameter("email");

        int age = Integer.parseInt(ageStr);

        User user = new User(name,age,email);

        System.out.println(user);
    }
}
