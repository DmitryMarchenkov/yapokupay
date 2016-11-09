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
    <script src="${pageContext.request.contextPath}/resources/js/scriptAddAdvert.js"></script>

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
                <a href="#" id="login">Log In</a>
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



            <form:form id="advertForm" method="post" action="advert/add" commandName="advert" acceptCharset="UTF-8">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <table>
                    <tr>
                        <td>title</td>
                        <td><form:input path="title" /></td>
                    </tr>
                    <tr>
                        <td>price</td>
                        <!--Notice, this is normal html tag, will not be bound to an object -->
                        <td><input name="price" type="text"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="send"/>
                        </td>
                    </tr>
                </table>
            </form:form>




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
