<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 17.07.2019
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your order</title>
</head>
<body>
<center>
    <h2> Order </h2>
    <form action="/order" method="post">
        Surname:<br>
        <input type="text" name="surname" value="">
        <br>
        Name:<br>
        <input type="text" name="name" value="">
        <br>
        City:<br>
        <input type="text" name="city" value="">
        <br>
        Address:<br>
        <input type="text" name="address" value="">
        <br>
        Phone:<br>
        <input type="number" name="phone" value="">
        <button type="submit"> Confirm</button>
        <button><a href="/sign"> Log Out </a></button>
    </form>
</center>
</body>
</html>

