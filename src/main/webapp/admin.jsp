<%--
  Created by IntelliJ IDEA.
  User: vadim.aminov
  Date: 30.05.2019
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of all users</title>
</head>
<body>
<a href = "/add"> Add user</a>
<table border="2">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Login</td>
        <td>Password</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getPassword}</td>
            <td><a href="/delete?id=${user.getId()}">Delete</a></td>
            <td><a href="/edit?id=${user.getId()}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
