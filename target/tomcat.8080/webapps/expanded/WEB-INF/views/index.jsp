<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Online Shop</title>
	<link rel="SHORTCUT ICON" href="/resources/images/shortIco.jpg" type="image/gif">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">

	<link href="/resources/css/style.css" rel="stylesheet"/>
	<link href="/resources/css/bootstrap.css" rel="stylesheet"/>

	<script src="/resources/js/jquery.min.js"></script>
	<script src="/resources/js/script.js"></script>

</head>
<body>

<div id="navbar">
	<a href="#" >
		<img id="logo" src="../../resources/images/logo.jpg">
	</a>
	<ul id="menu">

		<li>
			<a href="/home" class="nav_menu">Home</a>
		</li>
		<li>
			<a href="/about" class="nav_menu">About</a>
		</li>
		<li>
			<a href="/products" class="nav_menu">Products</a>
		</li>
		<li>
			<a href="/contacts" class="nav_menu">Contacts</a>
		</li>
	</ul>

	<div id="navbar_bottom">
		<div class="phone">
			<p>Call US</p>
			<p>(099)541-61-42</p>
		</div>
		<div class="copyright"> 2016 Right & Con</div>
	</div>
</div>

<div id="content">

	<div class="pg_home page">
		<div class="inner-page">
			HOME PAGE
            <img src="/resources/images/1.jpg">
            <img src="/resources/images/2.jpg">
		</div>
	</div>

	<div class="pg_about page">
		<div class="inner-page">

		</div>
	</div>

	<div class="pg_products page">
		<div class="inner-page">
            <div id="products">
            </div>
		</div>
	</div>

	<div class="pg_contacts page">
		<div class="inner-page">

		</div>
	</div>

</div>



<%--<h1>
        Add a Person
    </h1>

    <c:url var="addAction" value="/person/add" ></c:url>

    <form:form action="${addAction}" commandName="person">
        <table>
            <c:if test="${!empty person.name}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8"  disabled="true" />
                        <form:hidden path="id" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="price">
                        <spring:message text="Price"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="price" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Edit Person"/>" />
                    </c:if>
                    <c:if test="${empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Add Person"/>" />
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
    <br>--%>

</body>
</html>
