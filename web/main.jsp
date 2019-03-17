<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="title"/></title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div>

        <form action="${pageContext.request.contextPath}/individuals">
            <input type="submit" value=<fmt:message key="individuals.title"/> />
        </form>
        <form action="${pageContext.request.contextPath}/companies">
            <input type="submit" value=<fmt:message key="companies.title"/> />
        </form>

    </div>
</body>
</html>
