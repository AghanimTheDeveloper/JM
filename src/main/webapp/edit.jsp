<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 30.05.2019
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user page</title>
</head>
<body>
<form action="/edit" method="post">
    <table border="2">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <tr>
            <td><input type="text" name="name" value="${user.getName()}"/></td>
            <td><input type="text" name="login" value="${user.getLogin()}"/></td>
            <td><input type="text" name="password" value="${user.getPassword()}"/></td>
        </tr>
        <br><input type="submit" value="Edit user"></br>
    </table>
</form>
<a href="/admin">To main</a>
</body>
</html>
