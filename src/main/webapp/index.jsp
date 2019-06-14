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
    <title>Title</title>
</head>
<body>
<form action="/signup" method="post">
    <table border="2">
        <tr>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
            <td>Sign Up</td>
        </tr>
        <tr>
            <td><input type="text" name="name"/></td>
            <td><input type="text" name="login"/></td>
            <td><input type="password" name="password"/></td>
            <td><input type="submit" value="Sign Up"/></td>
        </tr>
    </table>
</form>
<form action="/signin" method="post">
    <table border="2">
        <tr>
            <td>Login</td>
            <td>Password</td>
            <td>Sign In</td>
        </tr>
        <tr>
            <td><input type="text" name="login"/></td>
            <td><input type="password" name="password"/></td>
            <td><input type="submit" value="Sign In"/></td>
        </tr>
    </table>
</form>
</body>
</html>
