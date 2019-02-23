<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="main.model.data.Database" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="individuals.title"/></title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/add/person/">
        <input type="submit" value=<fmt:message key="individuals.add"/> />
    </form> </br>

    <%--todo search, result to list--%>
    <%--todo open person info by clicking item in list--%>
    <form action="${pageContext.request.contextPath}/view/person/" method = "POST" target = "_parent">
        <input type="text" name="search" />
        <fmt:message key="individuals.search" var="search"/><input type="submit" value="${search}"> <Br>
    </form>

    <%
        // todo load from persons Database.getInstance().login()
    %>
</body>
</html>
