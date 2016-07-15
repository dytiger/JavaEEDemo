package org.forten.sample.jee.bo;

import org.forten.sample.jee.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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

    public static void main(String[] args) {
        User user = new User("Tom","123","M",new Date(),"sldk@k.com");
        try {
            doSave(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
