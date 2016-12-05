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

    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/script.js"></script>

</head>
<body>

<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="${contextPath}/resources/images/logo.jpg">
        </a>

        <div id="header_right">
            <a href="/addAdvert">
                <button type="button" class="btn btn-default">Добавить объявление</button>
            </a>

            <div class="profile">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <a href="/user/${pageContext.request.userPrincipal.name}" id="profile">${pageContext.request.userPrincipal.name}</a>
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

            <div class="dropdown">
                <button class="btn btn-primary btn-lg dropdown-toggle" type="button" data-toggle="dropdown">
                    ${category}
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="/all">Все категории</a></li>
                    <li><a href="/property">Недвижимость</a></li>
                    <li><a href="/auto">Авто</a></li>
                    <li><a href="/clothes">Одежда</a></li>
                    <li><a href="/technic">Техника</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="content">
        <div class="page">
            <c:if test="${!empty listAdverts}">
                <p>Все товары в категории <strong>${category}</strong></p>
                    <c:forEach items="${listAdverts}" var="advert">
                        <div class="advertItem">
                            <c:choose>
                                <c:when test="${advert.imagesCount > 0}">
                                    <a href="/obyavleniye/${advert.id}">
                                        <img src="${contextPath}/resources/uploadImages/${advert.authorUsername}/0.jpg">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <img src="/resources/images/bg-about.jpg">
                                </c:otherwise>
                            </c:choose>

                            <div class="advertInfo">
                                <h2>${advert.title}</h2>
                                <p>${advert.date}</p>
                            </div>
                            <div class="advertPrice">
                                <p>${advert.price} грн</p>
                            </div>
                        </div>
                    </c:forEach>
            </c:if>
        </div>
    </div>

    <footer>
        <p>
            @Ya Pokupay
        </p>
    </footer>
</div>

</body>
</html>
