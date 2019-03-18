<%@ page import="main.utils.constants.Constants" %>
<%@ page import="static main.utils.constants.Constants.PERSON" %>
<%@ page import="static main.utils.constants.Constants.FROM" %>
<%@ page import="static main.utils.constants.Constants.TO" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
<%@ page import="main.model.entities.Person" %>
<%@ page import="main.utils.constants.SdfConstants" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="main.model.entities.IndividualShipment" %>
<%@ page import="java.util.List" %>
<%@ page import="main.utils.ServletUtils" %>
<%@ page import="main.utils.DateUtils" %>
<%@ page import="java.sql.Date" %>
<%@ page import="javafx.util.Pair" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div>
    <%
        Person p = (Person) request.getAttribute(PERSON);
        String from = (String) request.getAttribute(FROM);
        String to = (String) request.getAttribute(TO);
        if (from == null || to == null) {
            Pair<Date, Date> lastMonth = DateUtils.getLastMonthPeriod();
            from = INPUT_DATE_FORMAT.format(lastMonth.getKey());
            to = INPUT_DATE_FORMAT.format(lastMonth.getValue());
        }
        String country = "";
        List<IndividualShipment> personShipments = (List<IndividualShipment>) request.getAttribute(Constants.SHIPMENTS);
//      --------------SHOW PERSON INFO--------------
        if (p != null) {
            country = ServletUtils.getCountry(getServletConfig(), p.getCountry()); %>
            <h3><%=p.getLastName() %> <%=p.getFirstName() %> <%=p.getPatronymic() %></h3>
            <h5><fmt:message key="individuals.view.date"/>: <%=SdfConstants.BIRTH_DATE_FORMAT.format(p.getBirthDate())%></h5>
            <h5><fmt:message key="individuals.view.passport"/>: <%=p.getPassport()%></h5>
            <h5><fmt:message key="individuals.view.country"/>: <%=country%></h5>
            <br><br>

            <%--SHOW SHIPMENTS IF PRESENT--%>
            <% if (personShipments != null && !personShipments.isEmpty()) { %>
                <h3><fmt:message key="individuals.view.shipments" /></h3> <br>
                <form action="${pageContext.request.contextPath}/individuals_view" method = "POST" target = "_parent">
                    <input type="hidden" name="id" value="<%=p.getPersonId()%>">
                    <label><fmt:message key="from"/>  <input type="date" name="from" value="<%=from%>"/></label>
                    <label><fmt:message key="to"/>  <input type="date" name="to" value="<%=to%>"/></label>
                    <fmt:message key="individuals.view.search" var="search"/><input type="submit" class="little" value="${search}"> <br>
                </form>

                <table>
                <%
                    float sum = 0;
                    for (IndividualShipment sh: personShipments) {
                        sum += sh.getPrice() * sh.getQuantity(); %>

                        <tr>
                            <td><%=sh.getDate()%> </td>
                            <td><%=sh.getProduct()%> </td>
                            <td><%=sh.getPrice()%> <fmt:message key="rub"/> </td>
                            <td><%=sh.getQuantity()%> <fmt:message key="individuals.view.pc"/><br> </td>
                        </tr><%
                    }
                    String sumFormatted = String.format("%.2f", sum); %>
                </table>
                <br>
                <h3 class="right"> <fmt:message key="sum"/> <%=sumFormatted%> <fmt:message key="rub"/> </h3> <%
                }
                %>

            <br><br><br><br>
            <form action="${pageContext.request.contextPath}/individuals_shipment_new">
                <input type="hidden" name="id" value="<%=p.getPersonId()%>" />
                <fmt:message key="individuals.view.add" var="add"/><input type="submit" value="${add}" />
            </form> <br>
            <%
        } else { %>
            <fmt:message key="individuals.view.error"/>
            <form action="${pageContext.request.contextPath}/individuals">
                <fmt:message key="individuals.view.back" var="back"/><input type="submit" value="${back}" />
            </form>
            <%
        }
    %>
    </div>
</body>
</html>
