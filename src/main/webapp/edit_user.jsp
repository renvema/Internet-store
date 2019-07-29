<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 08.07.2019
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<center>

    ${valid}
    <h4>Edit user data</h4>
    <form action="/admin/edit/user?id=${userId}" method="post">
        Email <input name="email" type="email" value="${oldEmail}"> <br>
        Password <input name="password" type="password" value="${oldPassword}"> <br>
        <input name="role" type="radio" value="admin"> admin
        <input name="role" type="radio" value="user" checked>user
        <input type="submit" value="Save">
    </form>

</center>
</body>
</html>
