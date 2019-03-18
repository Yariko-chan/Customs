
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div>
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
            <h3> <fmt:message key="export.title"/> </h3> <%
        } else { %>
            <h3> <fmt:message key="import.title"/> </h3> <%
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
        if (trades != null && !trades.isEmpty()) { %>
            <table> <%
            for (FullTrade t: trades) { %>
                <tr>
                    <td><%=t.getTrade().getDate()%> </td>
                    <td><%=t.getForeignCompany().getName()%> </td>
                    <td><%=t.getNationalCompany().getName()%> </td>
                    <td><fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%> </td>
                    <td><%=t.getTrade().getProduct()%> </td>
                    <td><%=t.getTrade().getPrice()%> <fmt:message key="rub"/> </td>
                    <td><%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> </td>
                </tr> <%
            } %>
            </table> <%
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %>
    </div>
</body>
</html>
