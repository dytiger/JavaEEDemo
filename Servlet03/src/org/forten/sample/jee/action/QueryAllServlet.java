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
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
@WebServlet(name = "QueryAllServlet",urlPatterns = "/user/list.do")
public class QueryAllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            List<User> list = UserBo.queryAll();

            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("用户列表");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=\"1\">");
            out.println("<tr><th>序号</th><th>姓名</th><th>生日</th><th>Email</th><th>性别</th><th>等级</th><th>注册时间</th><th>操作</th></tr>");
            for(int i = 0; i<list.size();i++){
                User u = list.get(i);
                out.print("<tr>");
                out.print("<td>");
                out.print(i+1);
                out.print("</td>");
                out.print("<td>");
                out.print(u.getName());
                out.print("</td>");
                out.print("<td>");
                out.print(u.getBirthday());
                out.print("</td>");
                out.print("<td>");
                out.print(u.getEmail());
                out.print("</td>");
                out.print("<td>");
                out.print(u.getGender());
                out.print("</td>");
                out.print("<td>");
                out.print(u.getLevel());
                out.print("</td>");
                out.print("<td>");
                out.print(u.getRegistTime());
                out.print("</td>");
                out.print("<td>");
                out.print("删除 修改");
                out.print("</td>");
                out.print("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
