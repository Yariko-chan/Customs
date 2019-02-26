<%@ page import="main.utils.constants.Constants" %>
<%@ page import="main.model.entities.Person" %>
<%@ page import="main.utils.constants.SdfConstants" %>
<%@ page import="java.util.HashMap" %>
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
        if (p != null) { %>
            <%=p.getLastName() %> <%=p.getFirstName() %> <%=p.getPatronymic() %> </br>
            <fmt:message key="individuals.view.date"/>: <%=SdfConstants.BIRTH_DATE_FORMAT.format(p.getBirthDate())%> </br>
            <fmt:message key="individuals.view.passport"/>: <%=p.getPassport()%> </br>
            <%
                HashMap<String, String> countriesMap = (HashMap<String, String>)
                        getServletConfig().getServletContext().getAttribute(Constants.COUNTRIES_MAP);
                String country = countriesMap.get(p.getCountry());
                if (country == null) {
                    country = "-";
                }
            %>
            <fmt:message key="individuals.view.country"/>: <%=country%> </br>

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
