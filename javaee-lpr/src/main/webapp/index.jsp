<%--
  Created by IntelliJ IDEA.
  User: mah454
  Date: 11/26/18
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/sync" method="post">
    <input type="text" name="message"><br>
    <input type="text" name="channel"><br>
    <input type="submit" value="send">
</form>
</body>
</html>
