<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
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
<% List<Product> allProduct = (List<Product>) request.getAttribute("allProduct");
%>

<center>
    <h4>Edit product data</h4>
    <form action="/edit/product" method="post">
        Title <input name="title" type="text"/> <br>
        Description <input name="description" type="text"/> <br>
        Price <input name="price" type="number" step="0.01"/> <br>
        ${valid}
        <button type="submit">Save</button>
    </form>

</center>
</body>
</html>
