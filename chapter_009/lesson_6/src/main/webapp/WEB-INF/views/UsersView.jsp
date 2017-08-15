<%@ page import="ru.dega.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 13.08.2017
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>mvc, filter, security</title>
</head>
<body>

<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <br>
    Hello <c:out value="${sessionScope.login}"/>, you role is <c:out value="${sessionScope.role}"/>
    <input type='submit' value='sign out'>
</form>

<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Login : <input type="text" name="login"><br>
    Password : <input type="text" name="password"><br>
    Name : <input type="text" name="name"><br>
    Email : <input type="text" name="email"><br>
    Role : <select id="role" name="role">
        <c:forEach items="${roles}" var="role">
            <option>${role}</option>
        </c:forEach>
    </select>
    <br>
    <input type='submit' value='create user'>
</form>

<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    Login : <input type="text" name="login"><br>
    New name : <input type="text" name="name"><br>
    New email : <input type="text" name="email"><br>
    <input type='submit' value='edit user'>
</form>

<form action="${pageContext.servletContext.contextPath}/delete" method="post">
    Login : <input type="text" name="login"><br>
    <input type='submit' value='delete user'>
</form>

<table style="border: 1pt solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>name</th>
        <th>email</th>
        <th>create date</th>
        <th>role</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td><c:out value="${user.role}"></c:out></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>