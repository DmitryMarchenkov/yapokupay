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

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>

<div id="wrapper">
    <div id="header">
        <a class="logo" href="/all" >
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
        <div class="page row">
            <div id="advert_desc" class="col-md-8 col-xs-12">
                <input type="hidden" name="advertId" value="${advert.id}">
                <h2><strong>${advert.title}</strong></h2>
                <div id="showed">
                    <p>
                        Добавлено: ${advert.date}
                    </p>

                    <p >
                        Просмотров: ${advert.viewCounter}
                    </p>
                </div>
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
                <p>
                    ${advert.description}<br/>
                </p>
            </div>

            <div id="user_info" class="col-md-4 col-xs-12">
                <div id="price"><strong>${advert.price} грн</strong></div>
                <p>Автор: ${user.name}</p>
                <c:if test="${user.phone != null}">
                    <p>Телефон: ${user.phone}</p>
                </c:if>
                <button type="button" class="btn btn-success btn-lg send_message_to_author">
                    Написать автору
                    <input type="hidden" value="${user.id}">
                </button>
            </div>
        </div>
    </div>

    <div id="shadowBackground">
        <div id="messageToAuthor">
            <form class="form-horizontal" id="messageForm">
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Ваш email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                        <input type="hidden" id="authName" value="${user.username}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="message" class="col-sm-2 control-label">Сообщение</label>
                    <div class="col-sm-10">
                        <textarea rows="10" class="form-control" id="message" placeholder="Введите текст сообщения!"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10 sendButton">
                            <button type="submit" class="btn btn-default">Отправить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <footer>
        <p>
            @Ya Pokupay
        </p>
    </footer>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/scriptShowAdvert.js"></script>
<script src="${contextPath}/resources/js/sendMessage.js"></script>

</body>
</html>
