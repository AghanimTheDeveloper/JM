<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 14.06.2019
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
    <table border="2">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword()}</td>
        </tr>
    </table>
</form>
</body>
</html>
