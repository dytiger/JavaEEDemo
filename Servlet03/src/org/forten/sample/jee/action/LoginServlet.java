package org.forten.sample.jee.action;

import org.forten.sample.jee.bo.UserBo;
import org.forten.sample.jee.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/7/21.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            User user = UserBo.login(name,password);
            if(user==null){
                request.setAttribute("msg","登录失败");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("loginedUser",user);
                response.sendRedirect("user/list2.do");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
