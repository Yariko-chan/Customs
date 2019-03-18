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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div>

    <%--NATIONAL COMPANIES--%>

    <h3><fmt:message key="companies.national"/></h3>

    <form action="${pageContext.request.contextPath}/national/search" method = "POST" target = "_parent">
        <label> <input type="text" name="search" placeholder="<fmt:message key="companies.national.search"/>" /> </label>
        <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <%
        List<NationalCompany> nationalCompanies = (List<NationalCompany>) request.getAttribute(NATIONAL);
        if (nationalCompanies != null && !nationalCompanies.isEmpty()) { %>
            <table> <%
            for (NationalCompany nc: nationalCompanies) { %>
                <tr>
                    <td> <%=nc.getName()%> </td>
                    <td> <fmt:message key="companies.unp"/> <%=nc.getUNP()%> </td>
                    <td> <fmt:message key="companies.license"/> <%=nc.getLicense()%> </td>
                    <td> <fmt:message key="companies.valid"/> <%=nc.getLicenseValidDate()%> </td>
                    <td> <form action="${pageContext.request.contextPath}/view/national">
                        <input type="hidden" name="id" value="<%=nc.getId()%>">
                        <input type="submit" value=">" />
                    </form> </td>
                </tr><%
            } %>
            </table> <%
        } else { %>
            <fmt:message key="individuals.search.empty"/> <%
        }
    %>
    <form action="${pageContext.request.contextPath}/new/national/">
        <input type="submit" value=<fmt:message key="add"/> />
    </form>

    <br>
    <br>
    <br>

    <%--FOREIGN COMPANIES--%>

    <h3><fmt:message key="companies.foreign"/></h3>

    <form action="${pageContext.request.contextPath}/foreign/search" method = "POST" target = "_parent">
        <label> <input type="text" name="search" placeholder="<fmt:message key="companies.foreign.search"/>" /> </label>
        <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <%
        List<ForeignCompany> foreignCompanies = (List<ForeignCompany>) request.getAttribute(FOREIGN);
        if (foreignCompanies != null && !foreignCompanies.isEmpty()) { %>
            <table> <%
            for (ForeignCompany fc: foreignCompanies) {
                String country = ServletUtils.getCountry(getServletConfig(), fc.getCountry()); %>
                <tr>
                    <td> <%=fc.getName()%> </td>
                    <td> <%=country%> </td>
                    <td> <form action="${pageContext.request.contextPath}/view/foreign">
                        <input type="hidden" name="id" value="<%=fc.getId()%>">
                        <input type="submit" value=">" />
                    </form> </td>
                </tr> <%
            } %>
            </table> <%
        } else { %>
            <fmt:message key="individuals.search.empty"/> <%
        }
    %>
    <form action="${pageContext.request.contextPath}/new/foreign/">
        <input type="submit" value=<fmt:message key="add"/> />
    </form>
    </div>
</body>
</html>
