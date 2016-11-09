<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Ya Pokupay</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</head>
<body>


<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="../../resources/images/logo.jpg">
        </a>

        <div id="header_right">
            <a href="/addAdvert">
                <button type="button" class="btn btn-default">Добавить объявление</button>
            </a>
            <div class="profile">
                <a href="#" id="profile">Гость</a>
                |
                <a href="<c:url value="/login" />" id="login">Log In</a>
                <a href="<c:url value="/logout" />" id="logout">Log Out</a>
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
                                <c:when test="${!empty advert.img1}">
                                    <img src="/resources/images/advertsImages/${advert.img1}">
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
