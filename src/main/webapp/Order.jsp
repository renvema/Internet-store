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
    <form action="/order" method="POST">
        First Name:<br>
        <input type="text" name="firstName" value="">
        <br>
        Last Name:<br>
        <input type="text" name="lastName" value="">
        <br>
        Number of phone:<br>
        <input type="number" name="phone" value="">
        <br>
        Street Name:<br>
        <input type="text" name="street" value="">
        <br>
        House Number:<br>
        <input type="number" name="house" value="">
        <br>
        Code from Email:<br>
        <input type="text" name="code" value=""> <br>
        <br>
        <c:if test="${ok != null}">
            ${ok}
        </c:if>
        <c:if test="${valid != null}">
            ${valid}
        </c:if>
        <c:if test="${wrongCode != null}">
            ${wrongCode}
        </c:if>
        <br>
        <button type="submit"> Confirm </button>
        <button><a href="/sign"> Log Out </a></button>
    </form>
</center>
</body>
</html>

