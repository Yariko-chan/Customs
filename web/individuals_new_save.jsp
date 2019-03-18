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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div>
        <%
            SaveResult result = (SaveResult) request.getAttribute(Constants.SAVE_RESULT);
            if (result.isInputError()) { %>
                <h3><fmt:message key="individuals.add.result.error.input"/></h3><%
            } else if (result.isSavingError()) { %>
                <h3><fmt:message key="individuals.add.result.error.adding"/></h3><%
            } else { %>
                <h3><fmt:message key="individuals.add.result.success"/></h3><%
            }
        %>
        <br/>
        <br/>
        <br/>

        <form action="${pageContext.request.contextPath}/individuals_new">
            <fmt:message key="individuals.add.result.add" var="add"/> <input type="submit" value="${add}" />
        </form>
        <form action="${pageContext.request.contextPath}/individuals">
            <fmt:message key="individuals.add.result.back" var="back"/><input type="submit" value="${back}" />
        </form>
    </div>
</body>
</html>
