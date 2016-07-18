package org.forten.sample.jee.bo;

import org.forten.sample.jee.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class UserBo {
    public static int doSave(User user) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "INSERT INTO hr.test_users (name,password,gender,birthday,email,regist_time,user_level,id) VALUES (?,?,?,?,?,?,?,hr.USER_SEQ.NEXTVAL)";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,user.getName());
        stat.setString(2,user.getPassword());
        stat.setString(3,user.getGender());
        stat.setDate(4,new java.sql.Date(user.getBirthday().getTime()));
        stat.setString(5,user.getEmail());
        stat.setDate(6,new java.sql.Date(user.getRegistTime().getTime()));
        stat.setInt(7,user.getLevel());

        int count = stat.executeUpdate();

        stat.close();
        conn.close();

        return count;
    }

    public static List<User> queryAll() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "SELECT id,name,email,birthday,gender,user_level,regist_time FROM hr.test_users WHERE 1=1 ORDER BY id DESC";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        List<User> list = new ArrayList<>();
        while (rs.next()){
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setName(rs.getString("name"));
            u.setBirthday(rs.getDate("birthday"));
            u.setGender(rs.getString("gender"));
            u.setLevel(rs.getInt("user_level"));
            u.setRegistTime(rs.getDate("regist_time"));

            list.add(u);
        }

        rs.close();
        stat.close();
        conn.close();

        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<User> list = queryAll();
        for(User u : list){
            System.out.println(u);
        }
    }
}
