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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
  <div>

    <h1><fmt:message key="login.welcome" /></h1> <Br>

    <form action="login" method = "POST" target = "_parent">
      <label><input type="text" name="login" placeholder="<fmt:message key="login.login" />"/></label> <Br>
      <label><input type="password" name="password" placeholder="<fmt:message key="login.password" />"/></label> <Br>
      <fmt:message key="login.submit" var="submit"/><input type="submit" value="${submit}"> <Br>
    </form>

  </div>
</body>
</html>
