<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Ya Pokupay</title>
    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

<div id="wrapper">
    <div id="header">
        <a class="logo" href="/">
            <img id="logo" src="${contextPath}/resources/images/logo.jpg">
        </a>

        <div id="header_right">
            <a href="/addAdvert" id="addAdvertButton">
                <button type="button" class="btn btn-default">Добавить объявление</button>
            </a>

            <div class="profile">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <a href="/user/${pageContext.request.userPrincipal.name}" id="profile">${pageContext.request.userPrincipal.name}</a>
                        |
                        <a href="<c:url value="/logout" />" id="logout">Выход</a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" id="profile">Гость</a>
                        |
                        <a href="<c:url value="/login" />" id="login">Вход</a>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="dropdown">
                <button class="btn btn-primary btn-lg dropdown-toggle" type="button" data-toggle="dropdown">
                    <p>${category}</p>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${categories}" var="category">
                        <li><a href="/${category.key}">${category.value}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id="searchLine">
            <input type="text" placeholder="Что вы ищете?"/>
        </div>
        <div id="chooseSort">
            <button class="btn" type="button">От дорогих к дешевым</button>
            <button class="btn" type="button">От дешевых к дорогим</button>
            <button class="btn" type="button">Самые новые</button>
            <button class="btn" type="button">Самые старые</button>
        </div>
    </div>

    <div id="content">
        <div class="page">
        </div>
    </div>

    <footer>
        <p>
            @Ya Pokupay
        </p>
    </footer>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/script.js"></script>

</body>
</html>
