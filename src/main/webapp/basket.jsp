<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 19.07.2019
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Your choose products</title>
</head>
<body>

<center>
    <h2>Your choose products </h2>

    <table border=1 bgcolor="#dda0dd">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="element" items="${products}">
        <tr>
            <td>${element.title}</td>
            <td>${element.description}</td>
            <td>${element.price}</td>
        </tr>
        </c:forEach>
        <button><a href="/login"> Started page </a></button>
        <button><a href="/order"> Order </a></button>
</center>
</body>
</html>
