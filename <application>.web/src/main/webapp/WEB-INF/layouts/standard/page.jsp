<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html lang="en">

<s:eval expression="@webProperties['application.version']" var="applicationVersion" scope="request"/>

<head>

    <title><tiles:insertAttribute name="title" defaultValue="<application-name>"/></title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta name="description" content="<application-name>"/>
    <meta name="keywords" content=""/>

    <%@ include file="resources.jspf" %>

</head>

<body>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="navigation"/>

<tiles:insertAttribute name="content"/>


<tiles:insertAttribute name="footer"/>

</body>
</html>
