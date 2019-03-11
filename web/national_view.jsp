<%@ page import="static main.utils.constants.Constants.COMPANY" %>
<%@ page import="main.model.entities.NationalCompany" %>
<%@ page import="static main.utils.constants.Constants.FROM" %>
<%@ page import="static main.utils.constants.Constants.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="main.utils.DateUtils" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
<%@ page import="main.controller.entities.FullTrade" %>
<%@ page import="java.util.List" %>
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

    <% // company data
        NationalCompany nc = (NationalCompany) request.getAttribute(COMPANY); %>
        <%=nc.getName()%> <br>
        <fmt:message key="companies.unp"/> <%=nc.getUNP()%> <br>
        <fmt:message key="companies.license"/> <%=nc.getLicense()%>
        <fmt:message key="companies.valid"/> <%=nc.getLicenseValidDate()%> <br> <%
    %>

    <br>

    <% // date
        String from = (String) request.getAttribute(FROM);
        String to = (String) request.getAttribute(TO);
        if (from == null || to == null) {
            Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
            from = INPUT_DATE_FORMAT.format(lastMonth.getKey());
            to = INPUT_DATE_FORMAT.format(lastMonth.getValue());
        }
    %>
    <form action="${pageContext.request.contextPath}/view/national" method = "POST" target = "_parent">
        <input type="hidden" name="id" value="<%=nc.getId()%>">
        <label><fmt:message key="from" /><input type="date" name="from" value="<%=from%>"/></label>
        <label><fmt:message key="to"/><input type="date" name="to" value="<%=to%>"/></label>
        <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <br>

    <fmt:message key="export.title"/> <br>
    <% // export list
        List<FullTrade> exports = (List<FullTrade>) request.getAttribute(EXPORT);
        float exportsSum = (float) request.getAttribute(EXPORT_SUM);
        String expSumFormatted = String.format("%.2f", exportsSum);
        if (exports != null && !exports.isEmpty()) {
            for (FullTrade t: exports) { %>
                <%=t.getTrade().getDate()%>
                <%=t.getForeignCompany().getName()%>
                <fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%>
                <%=t.getTrade().getProduct()%>
                <%=t.getTrade().getPrice()%> <fmt:message key="rub"/>
                <%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
            }  %>
            <fmt:message key="sum"/> <%=expSumFormatted%> <fmt:message key="rub"/> <br> <%
        } else {%>
            <fmt:message key="list.empty"/> <br> <%
        }
    %>

    <br>

    <fmt:message key="import.title"/> <br>
    <% // import list
        List<FullTrade> imports = (List<FullTrade>) request.getAttribute(IMPORT);
        float importsSum = (float) request.getAttribute(IMPORT_SUM);
        String impSumFormatted = String.format("%.2f", importsSum);
        if (imports != null && !imports.isEmpty()) {
            for (FullTrade t: imports) { %>
                <%=t.getTrade().getDate()%>
                <%=t.getForeignCompany().getName()%>
                <fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%>
                <%=t.getTrade().getProduct()%>
                <%=t.getTrade().getPrice()%> <fmt:message key="rub"/>
                <%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
            } %>
            <fmt:message key="sum"/> <%=impSumFormatted%> <fmt:message key="rub"/> <br> <%
        } else {%>
            <fmt:message key="list.empty"/> <br> <%
        }
    %>
</body>
</html>
