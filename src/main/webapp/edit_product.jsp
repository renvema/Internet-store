<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 11.07.2019
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<center>
    ${valid}
    <h4>Edit product data</h4>
    <form action="/admin/edit/product?id=${productId}" method="post">
        Title <input name="title" type="text" value="${oldTitle}"/> <br>
        Description <input name="description" type="text" value="${oldDescription}"/> <br>
        Price <input name="price" type="number" step="0.01" value="${oldPrice}"/> <br>
        <input type="submit" value="Save">
    </form>

</center>
</body>
</html>
