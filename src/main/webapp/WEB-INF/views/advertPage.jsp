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
            ${advert.id}<br/>
            ${advert.title}<br/>
            ${advert.price}<br/>
            ${advert.description}<br/>

                <div class="slider">
                    <ul>
                        <c:forEach items="${imagesCount}" var="i" varStatus="loop">
                            <li><img src="${contextPath}/resources/uploadImages/${advert.authorUsername}/${loop.index}.jpg" alt=""></li>
                        </c:forEach>
                        <%--${image}<br/>--%>
                        <%--${image1}<br/>--%>
                        <%--<img src="${image}" />--%>
                        <%--<img src="${image1}" />--%>

                        <%--<c:forEach items="${countImg}" var="i" varStatus="loop"><br/>--%>
                            <%--*: ${loop.index}<br/>--%>
                            <%--*: ${i}<br/>--%>
                            <%--<li><img src="${contextPath}/resources/uploadImages/${advert.authorUsername}/${loop.index}.jpg" alt=""></li>--%>
                            <%--&lt;%&ndash;*: ${loop}<br/>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;*: ${i.index}<br/>&ndash;%&gt;--%>
                        <%--</c:forEach>--%>
                    </ul>
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
