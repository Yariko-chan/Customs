<%@ page import="java.util.Calendar" %>
<%@ page import="java.sql.Date" %>
<%@ page import="static main.utils.constants.SdfConstants.INPUT_DATE_FORMAT" %>
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
</head>
<body>
    <%
        String defaultBirthDate = getDefaultBirthDate();
    %>
    <form action="${pageContext.request.contextPath}/new/person/save/" method = "POST" target = "_parent">
        <label><fmt:message key="individuals.add.name" /> <input type="text" name="name" /></label> <br>
        <label><fmt:message key="individuals.add.surname" /> <input type="text" name="surname" /> </label> <br>
        <label><fmt:message key="individuals.add.patronymic" /> <input type="text" name="patronymic" /> </label> <br>
        <label><fmt:message key="individuals.add.birthdate" /> <input type="date" name="birthdate" value="<%=defaultBirthDate%>"/> </label> <br>
        <label><fmt:message key="individuals.add.passport" /> <input type="text" name="passport" /> </label> <br>

        <label><select name="country">
            <c:forEach items="${sorted_countries_list}" var="list">
                <option value="${list.key}">${list.value}</option>
            </c:forEach>
        </select></label>

        <fmt:message key="individuals.add.add" var="submit"/><input type="submit" value="${submit}"> <Br>
    </form>
</body>
</html>

<%!
    private String getDefaultBirthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.YEAR, -18);
        return INPUT_DATE_FORMAT.format(c.getTime());
    }
%>