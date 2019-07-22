<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 05.07.2019
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список продуктов</title>
</head>
<body>

<center>
    <h2> All products </h2>

    <table border=1 bgcolor="#dda0dd">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Price</th>
            <th></th>
        </tr>
        <c:forEach var="element" items="${products}">
        <tr>
            <td>${element.title}</td>
            <td>${element.description}</td>
            <td>${element.price}</td>
            <td>
                <form method="post" action="/buy/product?id=${element.id}">
                    <input type="submit" value="Buy">
                </form>
            </td>
        </tr>
        </c:forEach>
        <button><a href="/login"> Started page </a></button>
        <button><a href="/basket"> Basket </a></button>
        <h3>${message}</h3>
        <p>Counter product ${size} <br>
</center>
</table>
</body>
</html>
