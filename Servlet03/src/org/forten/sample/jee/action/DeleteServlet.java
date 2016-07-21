package org.forten.sample.jee.action;

import org.forten.sample.jee.bo.UserBo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/7/19.
 */
@WebServlet(name = "DeleteServlet",urlPatterns = "/user/delete.do")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        try (PrintWriter out = response.getWriter()){
            UserBo.doDelete(id);
            out.print("id是"+id+"的用户已经成功删除");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
