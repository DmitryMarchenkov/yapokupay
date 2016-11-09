<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Титул</title>
</head>
<body>

<c:if test="${not empty param.error}">
    Ошибка входа
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
<form method="POST" action="/login">
    <table>
        <tr>
            <td align="right">Логин</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td align="right">Пароль</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td align="right">Запомнить меня</td>
            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

                <input type="submit" value="Login" />
                <input type="reset" value="Reset" /></td>
        </tr>
    </table>
</form>
</body>
</html>
