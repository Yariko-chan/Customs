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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>

    <div>

        <form action="${pageContext.request.contextPath}/individuals_new">
            <input type="submit" value=<fmt:message key="individuals.add"/> />
        </form> <br>
        <form action="${pageContext.request.contextPath}/individuals" method = "POST" target = "_parent">
            <label> <input type="text" name="search" placeholder="<fmt:message key="individuals.search.hint"/>"/> </label>
            <fmt:message key="individuals.search" var="search"/><input type="submit" class="little" value="${search}"> <br>
        </form>
        <table>
        <%
            List<Person> persons = (List<Person>) request.getAttribute(PERSONS);
            if (persons != null && !persons.isEmpty()) {
                for (Person p : persons) { %>
                    <tr>
                        <td><%=p.getLastName() %>  <%=p.getFirstName() %> <%=p.getPatronymic() %> </td>
                        <td><%=p.getPassport() %> </td>
                        <td><form action="${pageContext.request.contextPath}/individuals_view">
                            <input type="hidden" name="id" value="<%=p.getPersonId()%>">
                            <input type="submit" class="little" value=">" />
                        </form> </td>
                    </tr><%
                }
            } else { %>
                <fmt:message key="individuals.search.empty"/> <%
            }
        %>
        </table>
</div>
</body>
</html>
