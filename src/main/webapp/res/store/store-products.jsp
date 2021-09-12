<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/store-products" method="post">
<div class="container mt-3 mx-auto">
    <table class="table">
        <thead>
            <tr>
                <th scope="col">product_id</th>
                <th scope="col">Product Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Count</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.products}" var="product">
            <tr>
                <th scope="row">${product.id}</th>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <input type="number" class="count" name="${product.id}" value="0" min="0" max="20">
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-success " type="submit">Add to cart</button>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/receipts" role="button">Show my receipt</a>
</div>
</form>
</body>
</html>
