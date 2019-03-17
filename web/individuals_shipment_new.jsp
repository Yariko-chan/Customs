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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div>
        <%
            String defaultValidDate = getDefaultDate();
        %>
        <form action="${pageContext.request.contextPath}/individuals_add_shipment_result" method = "POST" target = "_parent">
            <label><fmt:message key="date" /> <input type="date" name="date" value="<%=defaultValidDate%>" /></label> <br>
            <label> <input type="text" name="product" placeholder="<fmt:message key="product" />"/></label> <br>
            <label> <input type="number" name="price"  step="0.01" placeholder="<fmt:message key="price" />"/></label> <fmt:message key="rub" /><br>
            <label> <input type="number" name="quantity" placeholder="<fmt:message key="quantity" />"/></label> <fmt:message key="individuals.view.pc" /> <br>
            <input type="hidden" name="id" value="<%=request.getParameter(Constants.ID)%>" />
            <fmt:message key="individuals.add.add" var="add"/><input type="submit" value="${add}"> <br>
        </form>
    </div>
</body>
</html>

<%!
    private String getDefaultDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>
