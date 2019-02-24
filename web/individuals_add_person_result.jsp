<%@ page import="main.utils.constants.Constants" %>
<%@ page import="main.controller.entities.SaveResult" %>
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
        SaveResult result = (SaveResult) request.getAttribute(Constants.SAVE_PERSON_RESULT);
        if (result.isInputError()) { %>
            <fmt:message key="individuals.add.result.error.input"/><%
        } else if (result.isSavingError()) { %>
            <fmt:message key="individuals.add.result.error.adding"/><%
        } else { %>
            <fmt:message key="individuals.add.result.success"/><%
        }
    %>

    <form action="${pageContext.request.contextPath}/new/person/">
        <input type="submit" value=<fmt:message key="individuals.add.result.add"/> />
    </form><form action="${pageContext.request.contextPath}/individuals">
        <input type="submit" value=<fmt:message key="individuals.add.result.back"/> />
    </form>
</body>
</html>
