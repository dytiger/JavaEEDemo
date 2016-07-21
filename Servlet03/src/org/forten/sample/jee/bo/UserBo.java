package org.forten.sample.jee.bo;

import org.forten.sample.jee.model.User;
import org.w3c.dom.stylesheets.StyleSheet;

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

    public static int doDelete(int id) throws SQLException, ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "DELETE FROM hr.test_users WHERE id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1,id);

        int count = stat.executeUpdate();
        stat.close();
        conn.close();
        return count;
    }

    public static User queryById(int id) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "SELECT id,name,email,birthday,gender FROM hr.test_users WHERE id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1,id);

        ResultSet rs = stat.executeQuery();

        if(!rs.next()){
            return null;
        }

        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setBirthday(rs.getDate("birthday"));
        u.setGender(rs.getString("gender"));

        rs.close();
        stat.close();
        conn.close();

        return u;
    }

    public static int doUpdate(User u) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "UPDATE hr.test_users SET name=?,email=?,birthday=?,gender=? WHERE id=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,u.getName());
        stat.setString(2,u.getEmail());
        stat.setDate(3,new java.sql.Date(u.getBirthday().getTime()));
        stat.setString(4,u.getGender());
        stat.setInt(5,u.getId());

        int count = stat.executeUpdate();

        stat.close();
        conn.close();

        return count;
    }

    public static int doModifyPassword(int id,String oldPwd,String newPwd) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "SELECT count(id) FROM hr.test_users WHERE id=? AND password=?";
        PreparedStatement stat = conn.prepareStatement(sql);

        stat.setInt(1,id);
        stat.setString(2,oldPwd);

        ResultSet rs = stat.executeQuery();
        rs.next();
        long count = rs.getLong(1);

        if(count == 0){
            return 0;
        }

        rs.close();
        stat.close();

        sql = "UPDATE hr.test_users SET password=? WHERE id=?";
        stat = conn.prepareStatement(sql);
        stat.setString(1,newPwd);
        stat.setInt(2,id);
        int updateCount = stat.executeUpdate();

        stat.close();
        conn.close();

        return updateCount;
    }

    public static User login(String name,String password) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","123456");

        String sql = "SELECT id,name,user_level FROM hr.test_users WHERE name=? AND password=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1,name);
        stat.setString(2,password);

        ResultSet rs = stat.executeQuery();
        if(!rs.next()){
            return null;
        }

        User user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString(2));
        user.setLevel(rs.getInt(3));

        rs.close();
        stat.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<User> list = queryAll();
        for(User u : list){
            System.out.println(u);
        }
    }
}
