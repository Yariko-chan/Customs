<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.Date" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
<%@ page import="static main.utils.constants.DbConstants.TYPE" %>
<%@ page import="static main.utils.constants.Constants.CONTRACTS" %>
<%@ page import="main.model.entities.Contract" %>
<%@ page import="java.util.List" %>
<%@ page import="main.controller.entities.TradeType" %>
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
        TradeType type = TradeType.valueOf(request.getParameter(TYPE));
        String defaultValidDate = getDefaultDate();
        List<Contract> contracts = (List<Contract>) request.getAttribute(CONTRACTS);
        if (type != null && contracts != null && !contracts.isEmpty()) { %>
            <form action="${pageContext.request.contextPath}/new/trade/save/" method = "POST" target = "_parent">
                <label><fmt:message key="date" /> <input type="date" name="date" value="<%=defaultValidDate%>"/> </label> <br>
                <label><fmt:message key="product" /> <input type="text" name="product" /></label> <br>
                <label><fmt:message key="price" /> <input type="number" name="price"  step="0.01"/></label> <fmt:message key="individuals.view.rub" /><br>
                <label><fmt:message key="quantity" /> <input type="number" name="quantity" /></label> <fmt:message key="individuals.view.pc" /> <br>

                <label><fmt:message key="trade.contract" />
                    <select name="contract">
                    <c:forEach items="<%=contracts%>" var="c">
                        <option value="${c.id}">№ ${c.contractNumber} <fmt:message key="contract.dateprefix" /> ${c.date}</option>
                    </c:forEach>
                </select></label> <br>

                <fmt:message key="add" var="submit"/><input type="submit" value="${submit}"> <br>
            </form>
            <form action="${pageContext.request.contextPath}/new/contract/">
                <input type="hidden" name="type" value="<%=type%>"/>
                <input type="submit" value="<fmt:message key="contract.add"/>" />
            </form> <%
        } else {%>
            <fmt:message key="error.unknown"/>
            <form action="${pageContext.request.contextPath}/new/trade/">
                <input type="hidden" name="type" value="<%=type%>" />
                <input type="submit" value="<fmt:message key="back" />" />
            </form> <%
        }
    %>

</body>
</html>

<%!
    private String getDefaultDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>