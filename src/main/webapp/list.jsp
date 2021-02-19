
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h1>List User</h1>
<table border="1", width="400", cellspacing="2", cellpadding="2">
    <tr style="background-color: yellow">
        <td>Id</td>
        <td>Name</td>
        <td>Email</td>
        <td>Country</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getCountry()}</td>
            <td>
                <a href="/users?action=edit&id=${user.id}">Edit</a>
            </td>
            <td>
                <a href="/users?action=edit&id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h2>
    <a href="/users?action=create">Add New User</a>
</h2>

</body>
</html>
