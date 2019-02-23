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
<form action="AddPerson" method = "POST" target = "_parent">
    <fmt:message key="individuals.shipment.date" /> <input type="date" name="date" /> <Br>
    <fmt:message key="individuals.shipment.product" /> <input type="text" name="product" /> <Br>
    <fmt:message key="individuals.shipment.price" /> <input type="number" name="price"  step="0.1"/> <Br>
    <fmt:message key="individuals.shipment.quantity" /> <input type="number" name="quantity" /> <Br>

    <fmt:message key="individuals.add.add" var="add"/><input type="submit" value="${add}"> <Br>
</form>
</body>
</html>
