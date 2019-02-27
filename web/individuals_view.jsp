<%@ page import="main.utils.constants.Constants" %>
<%@ page import="main.model.entities.Person" %>
<%@ page import="main.utils.constants.SdfConstants" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="main.model.entities.IndividualShipment" %>
<%@ page import="java.util.List" %>
<%@ page import="main.utils.ServletUtils" %>
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
    <%
        Person p = (Person) request.getAttribute(Constants.PERSON);
        String country = "";
        List<IndividualShipment> personShipments = (List<IndividualShipment>) request.getAttribute(Constants.SHIPMENTS);
//      --------------SHOW PERSON INFO--------------
        if (p != null) {
            country = ServletUtils.getCountry(getServletConfig(), p.getCountry()); %>
            <%=p.getLastName() %> <%=p.getFirstName() %> <%=p.getPatronymic() %> <br>
            <fmt:message key="individuals.view.date"/>: <%=SdfConstants.BIRTH_DATE_FORMAT.format(p.getBirthDate())%> <br>
            <fmt:message key="individuals.view.passport"/>: <%=p.getPassport()%> <br>
            <fmt:message key="individuals.view.country"/>: <%=country%> <br>

            <%--SHOW SHIPMENTS IF PRESENT--%>
            <% if (personShipments != null && !personShipments.isEmpty()) { %>

                <%--todo search, result to list--%>
                <form action="SearchShipments" method = "POST" target = "_parent">
                    <label><fmt:message key="individuals.view.from" /><input type="date" name="from" /></label>
                    <label><fmt:message key="individuals.view.to"/><input type="date" name="to" /></label>
                    <fmt:message key="individuals.view.search" var="search"/><input type="submit" value="${search}"> <br>
                </form>

                <%
                    for(IndividualShipment sh: personShipments) { %>
                        <%=sh.getDate()%> <%=sh.getProduct()%>
                        <%=sh.getPrice()%> <fmt:message key="individuals.view.rub"/>
                        <%=sh.getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
                    }
                }
            %>

            <form action="${pageContext.request.contextPath}/add/shipment/">
                <input type="hidden" name="id" value="<%=p.getPersonId()%>" />
                <input type="submit" value=<fmt:message key="individuals.view.add"/> />
            </form> <br>
            <%
        } else { %>
            <fmt:message key="individuals.view.error"/>
            <form action="${pageContext.request.contextPath}/individuals">
                <input type="submit" value=<fmt:message key="individuals.view.back"/> />
            </form>
            <%
        }
    %>
</body>
</html>
