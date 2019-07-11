<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
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


<%--<%--%>
<%--    PrintWriter printWriter = response.getWriter();--%>
<%--    printWriter.write("<center>");--%>
<%--    printWriter.write("<h2> All users </h2>");--%>
<%--    printWriter.write("<button><a href=" + "add_user.jsp" + ">Registration</a></button>");--%>
<%--    printWriter.write("<table border=\"1\" bgcolor=\"#dda0dd\">\n" +--%>
<%--            "    <th>Email</th>\n" +--%>
<%--            "    <th>Password</th>\n" +--%>
<%--            "    <th></th>\n" +--%>
<%--            "    <th></th>");--%>
<%--    List<User> allUsers = (List<User>) request.getAttribute("allUsers");--%>
<%--    for (User user : allUsers) {--%>
<%--        printWriter.write("<tr>");--%>
<%--        printWriter.write("<td>" + user.getEmail());--%>
<%--        printWriter.write("<td>" + user.getPassword());--%>
<%--        printWriter.write("<td>" + "<button><a href=" + "/edit/user?id=" + user.getId() + ">Edit</a></button>");--%>
<%--        printWriter.write("<td>" + "<button><a href=" + "/delete/user?id=" + user.getId() + ">Delete</a></button>");--%>
<%--        printWriter.write("</tr>");--%>
<%--    }--%>
<%--    printWriter.write("</center>");--%>
<%--    printWriter.write("<button><a href=" + "products.jsp" + ">Товары</a></button>");--%>
<%--%>--%>
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
                <td>
                    <button><a href="/edit/user?id=${element.id}">Edit</a></button>
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
