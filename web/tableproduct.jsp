<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
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

<%
    PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write("<h2> Список продуктов </h2>");
    printWriter.write("<table border=\"1\" bgcolor=\"#dda0dd\">\n" +
            "    <th>Title</th>\n" +
            "    <th>Description</th>\n" +
            "    <th>Price</th>");
    List<Product> allProduct = (List<Product>) request.getAttribute("allProduct");
    for (Product product : allProduct) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + product.getTitle());
        printWriter.write("<td>" + product.getDescription());
        printWriter.write("<td>" + product.getPrice());
        printWriter.write("</tr>");
    }
    printWriter.write("</center>");
%>

</body>
</html>
