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
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<% List<User> allUsers = (List<User>) request.getAttribute("allUsers");

    %>
%>
<center>
    <h4>Edit user data</h4>
<form action="/register" method="post">
    Email <input name="email" type="email" value="${email}"/> <br>
    Password <input name="password" type="password" value="${password}"/> <br>
       <button type="submit">Save</button>
</form>
    </center>
</body>
</html>
