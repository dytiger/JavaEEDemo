package org.forten.sample.jee.action;

import org.forten.sample.jee.bo.UserBo;
import org.forten.sample.jee.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
@WebServlet(name = "QueryAllServlet2",urlPatterns = "/user/list2.do")
public class QueryAllServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // 调用后台的业务逻辑，得到数据
            List<User> list = UserBo.queryAll();

            // 把得到的数据封闭到request对象的一个属性上
            // 属性名是自定义的
            // 第二个参数是Object类型的对象
            request.setAttribute("data",list);

            // 获得请求转发器
            RequestDispatcher rd = request.getRequestDispatcher("list2.jsp");
            // 执行转发
            rd.forward(request,response);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
