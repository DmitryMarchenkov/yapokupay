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
    </div>

    <div id="content">
        <div class="page">

                    <c:if test="${!empty user}">
                        <h2>Пользователь <strong>${user.username}</strong></h2>
                        <p>${user.password}</p>
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
