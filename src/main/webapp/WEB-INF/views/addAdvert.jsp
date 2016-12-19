<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Ya Pokupay</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>


<div id="wrapper">
    <div id="header">
        <a href="/all" class="logo">
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
        <div class="page">
           <p id="addAdvert">Добавить объявление</p>

            <form id="advertForm"  commandName="advert" acceptCharset="UTF-8">
                <table class="table">
                    <tr>
                        <td><strong>Заголовок: </strong></td>
                        <td><input name="title" type="text"/></td>
                    </tr>
                    <tr>
                        <td><strong>Цена: </strong></td>
                        <td><input name="price" type="text"/> грн</td>
                    </tr>
                    <tr>
                        <td><strong>Категория: </strong></td>
                        <td>
                            <select class="form-control" id="categories">
                                <c:forEach items="${categories}" var="category">
                                    <option>${category.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><strong>Состояние: </strong></td>
                        <td>
                            <select class="form-control" id="state">
                                <option>Новый</option>
                                <option>б/у</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <strong>Описание: </strong>
                <textarea class="form-control" rows="3" name="description" placeholder="Описание"></textarea>

                <strong>Добавить изображения: </strong>
                    <input type="file" class="file" name="file" multiple>
                <button type="submit" class="btn btn-success">Сохранить</button>
                <input hidden name="authorid" type="text" value="${pageContext.request.userPrincipal.name}"/>
            </form>
        </div>
    </div>

    <progress class="progress progress-striped progress-success" value="0" max="100">0%</progress>

    <div id="status"></div>

    <footer>
        <p>
            @Ya Pokupay
        </p>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scriptAddAdvert.js"></script>

</body>
</html>
