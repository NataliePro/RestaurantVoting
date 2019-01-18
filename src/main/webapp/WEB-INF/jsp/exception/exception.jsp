<%@ page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<div class="jumbotron">
    <div class="container text-center">
        <br>
        <h2>${typeMessage}</h2>
        <h3>${message}</h3>
    </div>
</div>
<!--
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
</body>
</html>