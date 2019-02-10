<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title><fmt:message key="login.label.welcome"/></title>
  </head>
  <body>
  тест
  "${language}"
  <fmt:message key="login.label.welcome" />

  </body>
</html>
