<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="../../resources/images/logo.jpg">
        </a>

        <div id="header_right">
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
                <a href="/all">
                    <button class="btn btn-primary btn-lg" type="button">
                        Отмена
                    </button>
                </a>
            </div>
        </div>
    </div>

    <div id="content">
        <form method="POST" action="${contextPath}/login" class="form-signin">
            <h2 class="form-heading">Вход</h2>
            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <input name="username" type="text" class="form-control" placeholder="Имя пользователя"
                       autofocus="true"/>
                <input name="password" type="password" class="form-control" placeholder="Пароль"/>
                <span>${error}</span>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
                <h4 class="text-center"><a href="${contextPath}/registration">Создать аккаунт</a></h4>
            </div>
        </form>
    </div>

    <footer>
        <p>
            @Ya Pokupay
        </p>
    </footer>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>