<%@ page import="main.utils.constants.Constants" %>
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
<form action="${pageContext.request.contextPath}/add/shipment/save/" method = "POST" target = "_parent">
    <label><fmt:message key="individuals.shipment.date" /> <input type="date" name="date" /></label> <br>
    <label><fmt:message key="individuals.shipment.product" /> <input type="text" name="product" /></label> <br>
    <label><fmt:message key="individuals.shipment.price" /> <input type="number" name="price"  step="0.1"/></label> <br>
    <label><fmt:message key="individuals.shipment.quantity" /> <input type="number" name="quantity" /></label> <br>
    <input type="hidden" name="id" value="<%=request.getParameter(Constants.ID)%>" />
    <fmt:message key="individuals.add.add" var="add"/><input type="submit" value="${add}"> <br>
</form>
</body>
</html>
