
<%@ page import="javafx.util.Pair" %>
<%@ page import="static main.utils.constants.Constants.*" %>
<%@ page import="main.controller.entities.FullTrade" %>
<%@ page import="main.controller.entities.TradeType" %>
<%@ page import="main.utils.DateUtils" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="companies.title"/></title>
</head>
<body>
    <%
        TradeType type = (TradeType) request.getAttribute(TYPE);
        List<FullTrade> trades = (List<FullTrade>) request.getAttribute(TRADES);
        String from = (String) request.getAttribute(FROM);
        String to = (String) request.getAttribute(TO);
        if (from == null || to == null) {
            Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
            from = INPUT_DATE_FORMAT.format(lastMonth.getKey());
            to = INPUT_DATE_FORMAT.format(lastMonth.getValue());
        }
    %>
    <% // header
        if (type == TradeType.EXPORT) { %>
            <fmt:message key="export.title"/> <%
        } else { %>
            <fmt:message key="import.title"/> <%
        }
    %>
    <% // date %>
        <form action="${pageContext.request.contextPath}/trades/view" method = "POST" target = "_parent">
            <input type="hidden" name="type" value="<%=type%>">
            <label><fmt:message key="from" /><input type="date" name="from" value="<%=from%>"/></label>
            <label><fmt:message key="to"/><input type="date" name="to" value="<%=to%>"/></label>
            <fmt:message key="search" var="search"/><input type="submit" value="${search}"> <br>
        </form>
    <% // trades
        if (trades != null && !trades.isEmpty()) {
            for (FullTrade t: trades) { %>
                <%=t.getTrade().getDate()%>
                <%=t.getForeignCompany().getName()%>
                <%=t.getNationalCompany().getName()%>
                <fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%>
                <%=t.getTrade().getProduct()%>
                <%=t.getTrade().getPrice()%> <fmt:message key="rub"/>
                <%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
            }
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %>

</body>
</html>
