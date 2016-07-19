<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/19
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<form action="update.do" method="post">
    <input type="hidden" name="id" value="${user.id}" />
    <div>
        <label>用户名</label>
        <input name="name" value="${user.name}" />
    </div>
    <div>
        <label>性别</label>
        <input name="gender" type="radio" value="M" <c:if test="${user.gender=='M'}">checked</c:if> />男</label>
        <input name="gender" type="radio" value="F" <c:if test="${user.gender=='F'}">checked</c:if> />女</label>
        <input name="gender" type="radio" value="U" <c:if test="${user.gender=='U'}">checked</c:if> />保密</label>
    </div>
    <div>
        <label>生日</label>
        <input name="birthday" type="date" value="${user.birthday}" />
    </div>
    <div>
        <label>Email</label>
        <input name="email" type="email" value="${user.email}" />
    </div>
    <div>
        <button>修改</button><button type="reset">恢复</button>
    </div>
</form>
</body>
</html>
