<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 03.07.2019
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<center>

    <form action="/add/product" method="post">
        Title <input name="title" type="text"/> <br>
        Description <input name="description" type="text"/> <br>
        Price <input name="price" type="number" step="0.01"/> <br>
        <button type="addproduct">Add product</button>
    </form>

</center>
</body>
</html>
