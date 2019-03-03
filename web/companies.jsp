<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="companies.title"/></title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/companies/list/">
        <input type="submit" value=<fmt:message key="companies.title"/> />
    </form> <br>

    <form action="${pageContext.request.contextPath}/new/export/">
        <fmt:message key="export.title"/>
        <input type="submit" value=<fmt:message key="export.add"/> />
    </form> <br>

    <%--todo list export top 10--%>

    <form action="${pageContext.request.contextPath}/new/import/">
        <fmt:message key="import.title"/>
        <input type="submit" value=<fmt:message key="import.add"/> />
    </form> <br>

    <%--todo list import top 10--%>
</body>
</html>
