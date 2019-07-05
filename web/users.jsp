<%@ page import="model.User" %>
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


<%
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write("<h2> Список пользователей </h2>");
    printWriter.write("<button><a href=" + "register.jsp" + ">Registration</a></button>");
    printWriter.write("<table border=\"1\" bgcolor=\"#dda0dd\">\n" +
            "    <th>Email</th>\n" +
            "    <th>Password</th>");
    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
    for (User user : allUsers) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + user.getEmail());
        printWriter.write("<td>" + user.getPassword());
        printWriter.write("</tr>");
    }
    printWriter.write("</center>");
%>


</body>
</html>
