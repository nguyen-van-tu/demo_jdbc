<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04-Feb-21
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit page</title>
</head>
<body>
<h1>Edit User</h1>
<a href="/users"><h2>Back to User list</h2></a>
<form method="post">
    <input type="text" name="name" value="${user.getName()}">
    <input type="text" name="email" value="${user.getEmail()}">
    <input type="text" name="country" value="${user.getCountry()}">
    <button type="submit">Edit</button>
</form>

</body>
</html>
