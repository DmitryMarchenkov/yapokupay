<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>

<div id="header">
    <a href="/all" >
        <img id="logo" src="../../resources/images/logo.jpg">
    </a>

    <div id="header_right">
        <a href="/addAdvert">
            <button type="button" class="btn btn-default">Добавить объявление</button>
        </a>
        <div class="profile">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <a href="#" id="profile">${pageContext.request.userPrincipal.name}</a>
                    |
                    <a href="<c:url value="/logout" />" id="logout">Log Out</a>
                </c:when>
                <c:otherwise>
                    <a href="#" id="profile">Гость</a>
                    |
                    <a href="<c:url value="/login" />" id="login">Log In</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>