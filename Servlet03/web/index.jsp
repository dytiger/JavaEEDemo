<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/21
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login.do" method="post">
    <div>
        <input name="name" placeholder="用户名" />
    </div>
    <div>
        <input name="password" type="password" placeholder="密码" />
    </div>
    <div>
        <button>登录</button>
    </div>
</form>
${msg}
</body>
</html>
