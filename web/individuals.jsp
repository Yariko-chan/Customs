<%@ page import="static main.utils.constants.Constants.ALL_PERSONS" %>
<%@ page import="main.model.entities.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<html>
<head>
    <title><fmt:message key="individuals.title"/></title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/new/person/">
        <input type="submit" value=<fmt:message key="individuals.add"/> />
    </form> </br>

    <%--todo search, result to list--%>
    <form action="${pageContext.request.contextPath}/view/person/" method = "POST" target = "_parent">
        <input type="text" name="search" />
        <fmt:message key="individuals.search" var="search"/><input type="submit" value="${search}"> <Br>
    </form>

    <%
        List<Person> persons = (List<Person>) request.getAttribute(ALL_PERSONS);
        for (Person p : persons) { %>
            <form action="${pageContext.request.contextPath}/view/person/">
                <%=p.getLastName() %> <%=p.getFirstName() %> <%=p.getPatronymic() %> <%=p.getPassport() %>
                <input type="hidden" name="id" value="<%=p.getPersonId()%>">
                <input type="submit" value=">" />
            </form> <%
        }
    %>
</body>
</html>
