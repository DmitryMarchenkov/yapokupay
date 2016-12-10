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
    <link href="${contextPath}/resources/css/styleShowAdvert.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>

    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/script.js"></script>
    <script src="${contextPath}/resources/js/scriptShowAdvert.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>

<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="${contextPath}/resources/images/logo.jpg">
        </a>

        <div id="header_right">
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
                <a href="/all">
                    <button class="btn btn-primary btn-lg" type="button">
                        Назад
                    </button>
                </a>
            </div>
        </div>
    </div>

    <div id="content">
        <div class="page">
            <input type="hidden" name="advertId" value="${advert.id}">
            ${advert.id}<br/>
            ${advert.title}<br/>
            ${advert.price}<br/>
            ${advert.description}<br/>
            ${imageNotFound}
            Просмотров: ${advert.viewCounter}
                <div class="slider">
                        <c:choose>
                            <c:when test="${imageNotFound}">
                                <img src="/resources/images/image_not_found.png">
                            </c:when>
                            <c:otherwise>
                                <ul>
                                    <c:forEach items="${imagesIds}" var="imageId" varStatus="loop">
                                        <li>
                                            <img src="/imagesDisplay?id=${imageId}" alt=""/>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:otherwise>
                        </c:choose>

                </div>
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
