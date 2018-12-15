<%@ page import="ir.moke.javaee.repository.AsyncContextRepository" %>
<%@ page import="java.util.List" %><%--
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

<h2>Current open channels</h2>
<p>[refresh to update list]</p>
<%
    AsyncContextRepository asr = new AsyncContextRepository() ;
    List<String> channels = asr.listChannels();
    for (String c : channels) {
        out.print(c + "<br>");
    }
%>

<br>
<form action="${pageContext.request.contextPath}/sync" method="post">
    <input type="text" name="message" placeholder="Enter your message"><br>
    <input type="text" name="channel" placeholder="target channel"><br>
    <input type="submit" value="send">
</form>
</body>
</html>
