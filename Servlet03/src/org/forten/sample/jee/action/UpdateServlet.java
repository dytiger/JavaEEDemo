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
 * Created by Administrator on 2016/7/19.
 */
@WebServlet(name = "UpdateServlet",urlPatterns = "/user/update.do")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        String name = request.getParameter("name");
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

        User user = new User(id,name,gender,birthday,email);

        try {
            UserBo.doUpdate(user);
            // 重定向
            response.sendRedirect("list2.do");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        User u = null;
        try (PrintWriter out = response.getWriter()){
            u = UserBo.queryById(id);
            if(u==null){
                out.print("<h1>此用户数据不存在</h1>");
            }else{
                request.setAttribute("user",u);
                request.getRequestDispatcher("update.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
