<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language
                                         : not empty language ? language
                                                              : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="individuals.title"/></title>
</head>
<body>
    <%--todo show data from request--%>
    Петров Пётр Петрович </br>
    Дата рождения: 11.07.2002 </br>
    паспорт 9210 161284 </br>
    Беларусь </br>
    <form action="SearchShipments" method = "POST" target = "_parent">
        <fmt:message key="individuals.view.from" /> <input type="date" name="from" />
        <fmt:message key="individuals.view.to" /> <input type="date" name="to" />
        <fmt:message key="individuals.view.search" var="search"/><input type="submit" value="${search}"> <Br>
    </form>
    <%--todo list of shipments--%>

    <form action="${pageContext.request.contextPath}/add/shipment/">
        <%--todo pass id--%>
        <input type="submit" value=<fmt:message key="individuals.view.add"/> />
    </form> </br>
</body>
</html>
