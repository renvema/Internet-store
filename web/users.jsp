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
            "    <th>Password</th>\n" +
            "    <th></th>\n" +
            "    <th></th>");
    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
    for (User user : allUsers) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + user.getEmail());
        printWriter.write("<td>" + user.getPassword());
        printWriter.write("<td>"+"<button><a href=" + "edit_user.jsp" + ">Edit</a></button>");
        printWriter.write("<td>"+"<button><a href=" + "delete_user.jsp" + ">Delete</a></button>");
        printWriter.write("</tr>");
     }

    printWriter.write("</center>");
    printWriter.write("<button><a href=" + "tableproduct.jsp" + ">Товары</a></button>");

%>


</body>
</html>
