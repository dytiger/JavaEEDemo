package org.forten.sample.jee.action;

import org.forten.sample.jee.bo.UserBo;
import org.forten.sample.jee.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/15.
 */
@WebServlet(name = "SaveUserServlet",urlPatterns = {"/user/save.do"})
public class SaveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthdayStr = request.getParameter("birthday");
        String email = request.getParameter("email");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday= sdf.parse(birthdayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(name,password,gender,birthday,email);

        PrintWriter out = response.getWriter();
        try {
            UserBo.doSave(user);
            response.sendRedirect("list2.do");
        } catch (ClassNotFoundException e) {
            out.print("<h1>用户注册失败</h1>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("<h1>用户注册失败，数据库出错</h1>");
            e.printStackTrace();
        }

        out.print("<h1>用户注册成功</h1>");
        out.close();
    }
}
