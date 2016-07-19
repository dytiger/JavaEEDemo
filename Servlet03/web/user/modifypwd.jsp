<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/19
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
    <form action="modifyPwd.do" method="post">
        <input name="oldPwd" /><br>
        <input name="newPwd" /><br>
        <button>修改密码</button>
    </form>
    ${errorMsg}
</body>
</html>
