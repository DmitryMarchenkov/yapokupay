<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Ya Pokupay</title>
    <link rel="shortcut icon" href="${contextPath}/resources/images/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/userPageStyle.css" rel="stylesheet"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="${contextPath}/resources/images/logo.jpg">
        </a>
    </div>

    <div id="content">


            <c:if test="${!empty user}">
                <h2>Пользователь <strong>${user.name}</strong></h2>
                <input name="user" type="hidden" value="${user.username}">
            </c:if>

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Меню</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav menu">
                            <li class="active"><a href="adverts">Мои объявления</a></li>
                            <li><a href="settings">Настройки</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="/">Назад</a></li>
                        </ul>
                    </div>
                </div>
            </nav>


        <div class="page">
        </div>

        <div class="settings">
            <h2>Пользователь <strong>${user.name}</strong></h2>
            <h2>имя пользователя <strong>${user.username}</strong></h2>
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
<script src="${contextPath}/resources/js/userPageScript.js"></script>

</body>
</html>
