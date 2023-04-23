<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Social Media Login Guide</title>
</head>
<body>
<h1>Social Media Login Guide</h1>
<p>Welcome to the social media login guide</p>
<p>You are currently authenticated!</p>
<p>Hello, ${username}</p>
<form method="post" action="logout">
    <button type="submit">Log out</button>
</form>
</body>
</html>