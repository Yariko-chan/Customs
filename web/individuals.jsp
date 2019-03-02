<%@ page import="static main.utils.constants.Constants.PERSONS" %>
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
    </form> <br>

    <%--todo search, result to list--%>
    <form action="${pageContext.request.contextPath}/individuals/search" method = "POST" target = "_parent">
        <fmt:message key="individuals.search" var="search"/>
        <label> <input type="text" name="search" /> </label>
        <fmt:message key="individuals.search" var="search"/><input type="submit" value="${search}"> <br>
    </form>

    <%
        List<Person> persons = (List<Person>) request.getAttribute(PERSONS);
        if (persons != null && !persons.isEmpty()) {
            for (Person p : persons) { %>
                <form action="${pageContext.request.contextPath}/view/person/">
                    <%=p.getLastName() %> <%=p.getFirstName() %> <%=p.getPatronymic() %> <%=p.getPassport() %>
                    <input type="hidden" name="id" value="<%=p.getPersonId()%>">
                    <input type="submit" value=">" />
                </form> <%
            }
        } else { %>
            <fmt:message key="individuals.search.empty"/> <%
        }
    %>
</body>
</html>
