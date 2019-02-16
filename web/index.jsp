<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title><fmt:message key="title"/></title>
  </head>
  <body>

  <fmt:message key="login.welcome" /> <Br>

  <form action="login" method = "POST" target = "_parent">
    <fmt:message key="login.login" /> <input type="text" name="login" /> <Br>
    <fmt:message key="login.password" /> <input type="password" name="password" /> <Br>
    <fmt:message key="login.submit" var="submit"/><input type="submit" value="${submit}"> <Br>
  </form>

  </body>
</html>
