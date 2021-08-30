<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<div class="card mx-auto mt-5" style="width: 40rem;">
    <div class="card-body">
        <h5 class="card-title text-center">Registration</h5>
        <br>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="mb-3 row">
                <label for="inputLogin" class="col-sm-2 col-form-label">Login:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputLogin" name="login">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword" name="password">
                </div>
            </div>
            <button class="btn btn-success" type="submit">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
