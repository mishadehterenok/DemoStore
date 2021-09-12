<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
        crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5 text-center">
        <h1>Welcome to my STORE!</h1>
        <br/>
        <div class="btn-group btn-group-lg">
            <a href="${pageContext.request.contextPath}/store-registration" class="btn btn-outline-primary ">Registration</a>
            <a href="${pageContext.request.contextPath}/store-sign" class="btn btn-outline-primary">Sign in</a>
        </div>
    </div>
</body>
</html>
