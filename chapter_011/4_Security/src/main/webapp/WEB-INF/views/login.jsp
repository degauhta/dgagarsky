<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mini avito login page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<h1>Login</h1>
<form name='f' action="${pageContext.servletContext.contextPath}/login.do" method='POST'>

    <c:if test="${not empty error}">
        <div class="error" style="color:#cc0000">${error}</div>
    </c:if>

    <c:if test="${not empty logout}">
        <div class="error" style="color:#95DEE3">${logout}</div>
    </c:if>

    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <c:choose>
            <c:when test="${not empty alreadyLogin}">
                <div class="error" style="color:#95DEE3">${alreadyLogin}</div>
                <tr>
                    <td><input name="submit" type="submit" value="submit" disabled/></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td><input name="submit" type="submit" value="submit"/></td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

</form>

</body>
</html>
