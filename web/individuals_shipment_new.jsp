<%@ page import="main.utils.constants.Constants" %>
<%@ page import="java.util.Calendar" %>
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
    <title><fmt:message key="individuals.shipment.new"/></title>
</head>
<body>
    <%
        String defaultValidDate = getDefaultDate();
    %>
    <form action="${pageContext.request.contextPath}/add/shipment/save/" method = "POST" target = "_parent">
        <label><fmt:message key="date" /> <input type="date" name="date" value="<%=defaultValidDate%>" /></label> <br>
        <label><fmt:message key="product" /> <input type="text" name="product" /></label> <br>
        <label><fmt:message key="price" /> <input type="number" name="price"  step="0.01"/></label> <fmt:message key="individuals.view.rub" /><br>
        <label><fmt:message key="quantity" /> <input type="number" name="quantity" /></label> <fmt:message key="individuals.view.pc" /> <br>
        <input type="hidden" name="id" value="<%=request.getParameter(Constants.ID)%>" />
        <fmt:message key="individuals.add.add" var="add"/><input type="submit" value="${add}"> <br>
    </form>
</body>
</html>

<%!
    private String getDefaultDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>
