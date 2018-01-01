<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mini avito</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="container">

    <div class="row">
        <p></p>
        <c:choose>
            <c:when test="${username!='anonymousUser'}">
                <div>Login as '${username}'</div>
                <a href='${pageContext.servletContext.contextPath}/logout.do'>Log out</a>
            </c:when>
            <c:otherwise>
                <a href='${pageContext.servletContext.contextPath}/login.do'>Log in</a>
            </c:otherwise>
        </c:choose>
    </div>

    <h2>Mini avito</h2>

    <form action="${pageContext.servletContext.contextPath}/adverts.do" method="post">
        desc : <input type="text" name="description">
        <br/><br/>
        car brand :
        <select name="carBrandId">
            <c:forEach var="item" items="${carBrands}">
                <option value="${item.key}">${item.value}</option>
            </c:forEach>
        </select>
        <br/><br/>
        car model :
        <select name="carModelId">
            <c:forEach var="item" items="${carModels}">
                <option value="${item.key}">${item.value}</option>
            </c:forEach>
        </select>
        <br/><br/>
        <c:choose>
            <c:when test="${username!='anonymousUser'}">
                <input type="submit" value="post advert">
            </c:when>
            <c:otherwise>
                <input type="submit" value="post advert" disabled>
            </c:otherwise>
        </c:choose>
        <br/><br/>
    </form>

    <table border="1">
        <tr>
            <td>advert id</td>
            <td>Description</td>
            <td>Car brand</td>
            <td>Car model</td>
        </tr>
        <c:forEach items="${adverts}" var="advert" varStatus="status">
            <tr valign="top">
                <td>${advert.id}</td>
                <td>${advert.description}</td>
                <td>${advert.carBrand.name}</td>
                <td>${advert.carModel.name}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>