<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Ya Pokupay</title>
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/images/shortcut-icon.jpg" type="image/gif">
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
        <a href="#" >
            <img id="logo" src="../../resources/images/logo.jpg">
        </a>

        <div id="header_right">
            <div class="profile">
                <a href="#" id="profile">Гость</a>
                |
                <a href="#" id="login">Log In</a>
            </div>

            <div class="dropdown">
                <button class="btn btn-primary btn-lg dropdown-toggle" type="button" data-toggle="dropdown">Все категории
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="#">Все категории</a></li>
                    <li><a href="#">Недвижимость</a></li>
                    <li><a href="/auto">Авто</a></li>
                    <li><a href="#">Одежда</a></li>
                    <li><a href="/technic">Техника</a></li>
                </ul>
            </div>
        </div>



    </div>

    <div id="content">

        <div class="page">
            <div class="inner-page">
                HOME PAGE
                <img src="/resources/images/shortcut-icon.jpg">
            </div>
        </div>

    </div>


</div>

</body>
</html>
