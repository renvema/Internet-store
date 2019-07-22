<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 22.07.2019
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
<form action="/confirm" method="post">
    <br>
    Code from Email:<br>
    <input type="text" name="code" value=""> <br>
    <button type="submit"> Confirm </button>
</form>
${message}
</body>
</html>
