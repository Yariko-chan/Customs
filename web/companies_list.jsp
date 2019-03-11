<%@ page import="main.model.entities.NationalCompany" %>
<%@ page import="java.util.List" %>
<%@ page import="main.model.entities.ForeignCompany" %>
<%@ page import="static main.utils.constants.Constants.NATIONAL" %>
<%@ page import="static main.utils.constants.Constants.FOREIGN" %>
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
    <title><fmt:message key="companies.title"/></title>
</head>
<body>

    <%--NATIONAL COMPANIES--%>

    <form action="${pageContext.request.contextPath}/new/national/">
        <fmt:message key="companies.national"/>
        <input type="submit" value=<fmt:message key="add"/> />
    </form>

    <form action="${pageContext.request.contextPath}/national/search" method = "POST" target = "_parent">
        <fmt:message key="companies.national.search"/> <br>
        <label> <input type="text" name="search" /> </label>
        <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <%
        List<NationalCompany> nationalCompanies = (List<NationalCompany>) request.getAttribute(NATIONAL);
        if (nationalCompanies != null && !nationalCompanies.isEmpty()) {
            for (NationalCompany nc: nationalCompanies) { %>
                <form action="${pageContext.request.contextPath}/view/national">
                    <%=nc.getName()%> <fmt:message key="companies.unp"/> <%=nc.getUNP()%>
                    <fmt:message key="companies.license"/> <%=nc.getLicense()%>
                    <fmt:message key="companies.valid"/> <%=nc.getLicenseValidDate()%>
                    <input type="hidden" name="id" value="<%=nc.getId()%>">
                    <input type="submit" value=">" />
                </form> <%

            }
        } else { %>
            <fmt:message key="individuals.search.empty"/> <%
        }
    %>

    <br>
    <br>
    <br>

    <%--FOREIGN COMPANIES--%>

    <form action="${pageContext.request.contextPath}/new/foreign/">
        <fmt:message key="companies.foreign"/>
        <input type="submit" value=<fmt:message key="add"/> />
    </form>

    <form action="${pageContext.request.contextPath}/foreign/search" method = "POST" target = "_parent">
        <fmt:message key="companies.foreign.search"/> <br>
        <label> <input type="text" name="search" /> </label>
        <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <%
        List<ForeignCompany> foreignCompanies = (List<ForeignCompany>) request.getAttribute(FOREIGN);
        if (foreignCompanies != null && !foreignCompanies.isEmpty()) {
            for (ForeignCompany fc: foreignCompanies) {
                String country = ServletUtils.getCountry(getServletConfig(), fc.getCountry()); %>
                <form action="${pageContext.request.contextPath}/view/foreign">
                    <%=fc.getName()%> <%=country%>
                    <input type="hidden" name="id" value="<%=fc.getId()%>">
                    <input type="submit" value=">" />
                </form> <%

            }
        } else { %>
            <fmt:message key="individuals.search.empty"/> <%
        }
    %>

</body>
</html>
