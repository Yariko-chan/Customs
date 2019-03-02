<%@ page import="main.utils.constants.Constants" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="main.resources.text"  />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title><fmt:message key="error"/></title>
</head>
<body>

    <%
        String error = (String) request.getAttribute(Constants.MSG);
    %>
    <fmt:message key="error.msg" />
    <%
        if (error == null) {%>
            <fmt:message key="error.unknown" />
        <% } else  {%>
            <%=error%> <%
        }
    %>
    <Br>

    <form action="${pageContext.request.contextPath}/index.jsp" method = "POST" target = "_parent">
        <fmt:message key="login.submit" var="submit"/><input type="submit" value="<fmt:message key="main" />"> <Br>
    </form>

</body>
</html>
