<%@ page import="main.controller.entities.TradeType" %>
<%@ page import="main.model.entities.Trade" %>
<%@ page import="java.util.List" %>
<%@ page import="static main.utils.constants.Constants.EXPORT" %>
<%@ page import="static main.utils.constants.Constants.IMPORT" %>
<%@ page import="main.controller.entities.FullTrade" %>
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

    <form action="${pageContext.request.contextPath}/companies/list/">
        <input type="submit" value=<fmt:message key="companies.title"/> />
    </form> <br>

    <form action="${pageContext.request.contextPath}/new/trade/">
        <fmt:message key="export.title"/>
        <input type="hidden" name="type" value="<%=TradeType.EXPORT%>">
        <input type="submit" value=<fmt:message key="export.add"/> />
    </form>
    <%
        List<FullTrade> export = (List<FullTrade>) request.getAttribute(EXPORT);
        if (export != null && !export.isEmpty()) {
            for (FullTrade t: export) { %>
                <%=t.getTrade().getDate()%>
                <fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%>
                <%=t.getTrade().getProduct()%>
                <%=t.getTrade().getPrice()%> <fmt:message key="individuals.view.rub"/>
                <%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
            }
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %> <br><br>

    <form action="${pageContext.request.contextPath}/new/trade/">
        <fmt:message key="import.title"/>
        <input type="hidden" name="type" value="<%=TradeType.IMPORT%>">
        <input type="submit" value=<fmt:message key="import.add"/> />
    </form>
    <%
        List<FullTrade> imports = (List<FullTrade>) request.getAttribute(IMPORT);
        if (imports != null && !imports.isEmpty()) {
            for (FullTrade t: imports) { %>
                <%=t.getTrade().getDate()%>
                <fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%>
                <%=t.getTrade().getProduct()%>
                <%=t.getTrade().getPrice()%> <fmt:message key="individuals.view.rub"/>
                <%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/> <br> <%
            }
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %>
</body>
</html>
