<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 14.08.2017
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<br>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login : <input type="text" name="login"><br>
    Password : <input type="password" name="password"><br>
    <input type="submit" value="sign in">
</form>
</body>
</html>
