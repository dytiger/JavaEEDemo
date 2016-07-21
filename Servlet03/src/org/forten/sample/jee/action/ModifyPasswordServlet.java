package org.forten.sample.jee.action;

import org.forten.sample.jee.bo.UserBo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/7/19.
 */
@WebServlet(name = "ModifyPasswordServlet",urlPatterns = "/user/modifyPwd.do")
public class ModifyPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 1;// TODO 这里的值应该从session范围中得到

        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");

        try {
            int count = UserBo.doModifyPassword(id,oldPwd,newPwd);
            if(count == 0){
                request.setAttribute("errorMsg","修改密码失败");
                request.getRequestDispatcher("modifypwd.jsp").forward(request,response);
            }else {
                response.sendRedirect("list2.do");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
