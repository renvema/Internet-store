<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 04.07.2019
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>

<center>
    <h2> All users </h2>

    <button><a href="/add/user"> Add user </a></button>
    <button><a href="/products">Products</a></button>
    <table border=1 bgcolor="#dda0dd">
        <tr>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <c:forEach var="element" items="${allUsers}">
            <tr>
                <td>${element.email}</td>
                <td>${element.password}</td>
                <td>${element.role}</td>
                <td>
                    <button><a href="/admin/edit/user?id=${element.id}">Edit</a></button>
                </td>
                <td>
                    <button><a href="/delete/user?id=${element.id}">Delete</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
