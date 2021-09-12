<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lesson Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<div class="container-fluid mt-5">
<h1>Welcome!</h1>
<br/>
<div class="btn-group">
    <a href="${pageContext.request.contextPath}/registration" class="btn btn-outline-primary">Registration</a>
    <a href="${pageContext.request.contextPath}/sign" class="btn btn-outline-primary">Sign in</a>
</div>
</div>
</body>
</html>