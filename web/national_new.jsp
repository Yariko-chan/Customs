<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.Date" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
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
    <title><fmt:message key="companies.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <%
        String defaultValidDate = getDefaultValidDate();
    %>
    <form action="${pageContext.request.contextPath}/new/national/save/" method = "POST" target = "_parent">
        <label><fmt:message key="name" /> <input type="text" name="name" /></label> <br>
        <label><fmt:message key="companies.unp" /> <input type="text" name="unp" /> </label> <br>
        <label><fmt:message key="companies.license" /> <input type="text" name="license" /> </label> <br>
        <label><fmt:message key="companies.valid" /> <input type="date" name="valid_date" value="<%=defaultValidDate%>"/> </label> <br>


        <fmt:message key="add" var="submit"/><input type="submit" value="${submit}"> <Br>
    </form>
</body>
</html>

<%!
    private String getDefaultValidDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.YEAR, +1);
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>