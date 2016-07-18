<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/18
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<table border="1">
    <tr><th>序号</th><th>姓名</th><th>生日</th><th>Email</th><th>性别</th><th>等级</th><th>注册时间</th><th>操作</th></tr>
    <c:forEach items="${data}" var="u" varStatus="s">
        <tr>
            <td>${s.index+1}</td>
            <td>${u.name}</td>
            <td>${u.birthday}</td>
            <td>${u.email}</td>
            <td>
                <c:if test="${u.gender=='M'}">男</c:if>
                <c:if test="${u.gender=='F'}">女</c:if>
                <c:if test="${u.gender=='U'}">保密</c:if>
            </td>
            <td>
                <c:choose>
                    <c:when test="${u.level==0}">普通用户</c:when>
                    <c:when test="${u.level==1}">一级管理员</c:when>
                    <c:when test="${u.level==2}">二级管理员</c:when>
                    <c:otherwise>未知等级</c:otherwise>
                </c:choose>
            </td>
            <td>${u.registTime}</td>
            <td>YYY</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
