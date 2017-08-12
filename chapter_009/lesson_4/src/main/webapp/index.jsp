<%@ page import="ru.dega.models.User" %>
<%@ page import="ru.dega.DBManager" %><%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 11.08.2017
  Time: 7:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jsp</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    Login : <input type="text" name="login"><br>
    Name : <input type="text" name="name"><br>
    Email : <input type="text" name="email"><br>
    <input type='submit' value='create user'>
</form>
<form action="<%=request.getContextPath()%>/edit" method="post">
    Login : <input type="text" name="login"><br>
    New name : <input type="text" name="name"><br>
    New email : <input type="text" name="email"><br>
    <input type='submit' value='edit user'>
</form>
<br>
<form action="<%=request.getContextPath()%>/delete" method="post">
    Login : <input type="text" name="login"><br>
    <input type='submit' value='delete user'>
</form>
<br>
<table style="border: 1pt solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>name</th>
        <th>email</th>
        <th>create date</th>
    </tr>
    <% for (User user : DBManager.getInstance().getAllEntries()) {%>
    <tr>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getCreateDate().toString()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>