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

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/scriptAddAdvert.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>


<div id="wrapper">
    <div id="header">
        <a href="/all" >
            <img id="logo" src="../../resources/images/logo.jpg">
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
                        Отмена
                    </button>
                </a>
            </div>
        </div>
    </div>


    <div id="content">
        <div class="page">
           <h2>Добавить объявление</h2>

            <form id="advertForm"  commandName="advert" acceptCharset="UTF-8">

                <table>
                    <tr>
                        <td>title</td>
                        <td><input name="title" type="text"/></td>
                    </tr>
                    <tr>
                        <td>price</td>
                        <td><input name="price" type="text"/></td>
                    </tr>
                    <tr>
                        <td>description</td>
                        <td><input name="description" type="text"/></td>
                    </tr>
                    <tr>
                        <td>category</td>
                        <td>
                            <select class="form-control" id="categories">
                                <option>Авто</option>
                                <option>Техника</option>
                                <option>Недвижимость</option>
                                <option>Одежда</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>state</td>
                        <td>
                            <select class="form-control" id="state">
                                <option>Новый</option>
                                <option>б/у</option>
                            </select>
                        </td>
                    </tr>

                    <input type="file" class="file" name="file" multiple>






                    <input hidden name="authorid" type="text" value="${pageContext.request.userPrincipal.name}"/>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="send"/>
                        </td>
                    </tr>
                </table>
            </form>




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
