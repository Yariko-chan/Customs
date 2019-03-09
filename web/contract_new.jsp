<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.Date" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
<%@ page import="main.controller.entities.Trade" %>
<%@ page import="static main.utils.constants.Constants.TYPE" %>
<%@ page import="main.model.entities.ForeignCompany" %>
<%@ page import="java.util.List" %>
<%@ page import="main.model.entities.DbResult" %>
<%@ page import="main.model.entities.NationalCompany" %>
<%@ page import="static main.utils.constants.Constants.FOREIGN" %>
<%@ page import="static main.utils.constants.Constants.*" %>
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
    <%
        String defaultValidDate = getDefaultDate();
        String type = request.getParameter(TYPE);
        List<ForeignCompany> foreignCompanies = (List<ForeignCompany>) request.getAttribute(FOREIGN);
        List<NationalCompany> nationalCompanies = (List<NationalCompany>) request.getAttribute(NATIONAL);
        if (type != null && foreignCompanies != null &&
                nationalCompanies != null) { %>
            <form action="${pageContext.request.contextPath}/new/contract/save/" method = "POST" target = "_parent">
                <label><fmt:message key="date" /> <input type="date" name="date" value="<%=defaultValidDate%>"/> </label> <br>
                <label><fmt:message key="number" /> <input type="text" name="number" /></label> <br>

                <% // FOREIGN
                Trade tradeType = Trade.valueOf(type);
                if (tradeType == Trade.EXPORT) { %> <%--foreign -> receiver--%>
                    <fmt:message key="contract.recipient"/> <%
                } else { %> <%-- IMPORT foreign -> supplier --%>
                    <fmt:message key="contract.supplier"/> <%
                } %>
                <label><select name="foreign">
                    <c:forEach items="<%=foreignCompanies%>" var="c">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select></label> <br><%

                // NATIONAL
                if (tradeType == Trade.EXPORT) { %> <%-- national -> supplier--%>
                    <fmt:message key="contract.supplier"/> <%
                } else { %> <%-- IMPORT national -> receiver --%>
                    <fmt:message key="contract.recipient"/> <%
                } %>
                <label><select name="national">
                    <c:forEach items="<%=nationalCompanies%>" var="c">
                        <option value="${c.id}">${c.name} <fmt:message key="companies.unp"/> ${c.UNP}</option>
                    </c:forEach>
                </select></label> <br>
                <input type="hidden" name="type" value="<%=type%>" />
                <fmt:message key="add" var="submit"/><input type="submit" value="${submit}"/> <br>
            </form> <%
        } else { %>
            <fmt:message key="error.unknown"/>
            <form action="${pageContext.request.contextPath}/new/contract/">
                <input type="hidden" name="type" value="<%=type%>" />
                <input type="submit" value="<fmt:message key="back" />" />
            </form> <%
        } %>
</body>
</html>

<%!
    private String getDefaultDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>