<%@ page import="org.forten.sample.jee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.forten.sample.jee.bo.UserBo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<table border="1">
    <tr><th>序号</th><th>姓名</th><th>生日</th><th>Email</th><th>性别</th><th>等级</th><th>注册时间</th><th>操作</th></tr>
    <%
        List<User> list = UserBo.queryAll();
        for(int i = 0;i<list.size();i++){
            User u = list.get(i);
    %>
    <tr>
        <td><%out.print(i+1);%></td>
        <td><%out.print(u.getName());%></td>
        <td><%out.print(u.getBirthday());%></td>
        <td><%out.print(u.getEmail());%></td>
        <td><%out.print(u.getGender());%></td>
        <td><%out.print(u.getLevel());%></td>
        <td><%out.print(u.getRegistTime());%></td>
        <td>XXX</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
