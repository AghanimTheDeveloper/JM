<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 30.05.2019
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Add user page</title>
</head>
<body>
<form action="/add" method="post">
    <table border="2">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <tr>
            <td><input type="text" name="name"/></td>
            <td><input type="text" name="login"/></td>
            <td><input type="text" name="password"/></td>
        </tr>
        <br><input type="submit" value="Add user"></br>
    </table>
</form>
<a href="/admin">To main</a>
</body>
</html>
