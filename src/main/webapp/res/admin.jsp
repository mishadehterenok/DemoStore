<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<ul>
    <c:forEach var="user" items="${requestScope.users}">
        <li>User: ${user.login} Login: ${user.password}</li>
    </c:forEach>
</ul>
</body>
</html>
