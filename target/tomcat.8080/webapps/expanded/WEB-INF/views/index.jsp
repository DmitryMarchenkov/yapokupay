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
            </div>
        </div>

    </div>

    <div class="page">
        <div class="inner-page">
            <c:if test="${!empty listAdverts}">
                <p>Все товары в категории <strong>Авто</strong></p>
                <table class="tg">
                    <tr>
                        <th width="80">ID</th>
                        <th width="120">Title</th>
                        <th width="120">Price</th>
                        <th width="120">Description</th>
                        <th width="120">Category</th>
                        <th width="120">Date</th>
                        <th width="60">Edit</th>
                        <th width="60">Delete</th>
                    </tr>
                    <c:forEach items="${listAdverts}" var="advert">
                        <tr>
                            <td>${advert.id}</td>
                            <td>${advert.title}</td>
                            <td>${advert.price}</td>
                            <td>${advert.description}</td>
                            <td>${advert.category}</td>
                            <td>${advert.category}</td>
                            <td><a href="<c:url value='/edit/${advert.id}' />" >Edit</a></td>
                            <td><a href="<c:url value='/remove/${advert.id}' />" >Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>

</div>

</body>
</html>
