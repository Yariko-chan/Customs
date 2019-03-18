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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/companies_list">
            <fmt:message key="companies.title" var="title"/><input type="submit" value="${title}" />
        </form> <br>

        <%--EXPORT--%>
    <%
        List<FullTrade> export = (List<FullTrade>) request.getAttribute(EXPORT);
        if (export != null && !export.isEmpty()) { %>
        <h3><fmt:message key="export.title"/></h3> <br>
            <table> <%
            for (FullTrade t: export) { %>
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
            </table>

            <form action="${pageContext.request.contextPath}/trades/view">
                <input type="hidden" name="type" value="<%=TradeType.EXPORT%>">
                <fmt:message key="all" var="all"/><input type="submit" value='${all}' />
            </form> <%
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %>
        <form action="${pageContext.request.contextPath}/new/trade/">
            <input type="hidden" name="type" value="<%=TradeType.EXPORT%>">
            <fmt:message key="export.add" var="add"/><input type="submit" class="little" value="${add}" />
        </form>
        <br><br>

        <%--IMPORT--%>
        <h3><fmt:message key="import.title"/></h3> <br>
    <%
        List<FullTrade> imports = (List<FullTrade>) request.getAttribute(IMPORT);
        if (imports != null && !imports.isEmpty()) { %>
            <table> <%
            for (FullTrade t: imports) { %>
                <tr>
                    <td><%=t.getTrade().getDate()%></td>
                    <td><%=t.getNationalCompany().getName()%></td>
                    <td><%=t.getForeignCompany().getName()%></td>
                    <td><fmt:message key="contract.number"/> <%=t.getContract().getContractNumber()%></td>
                    <td><%=t.getTrade().getProduct()%></td>
                    <td><%=t.getTrade().getPrice()%> <fmt:message key="rub"/></td>
                    <td><%=t.getTrade().getQuantity()%> <fmt:message key="individuals.view.pc"/></td>
                </tr> <%
            } %>
            </table>

            <form action="${pageContext.request.contextPath}/trades/view">
                <input type="hidden" name="type" value="<%=TradeType.IMPORT%>">
                <fmt:message key="all" var="all"/><input type="submit" value='${all}' />
            </form> <%
        } else {%>
            <fmt:message key="list.empty"/> <%
        }
    %>
        <form action="${pageContext.request.contextPath}/new/trade/">
            <input type="hidden" name="type" value="<%=TradeType.IMPORT%>">
            <fmt:message key="import.add" var="add"/><input type="submit" value="${add}" />
        </form>
    </div>
</body>
</html>
