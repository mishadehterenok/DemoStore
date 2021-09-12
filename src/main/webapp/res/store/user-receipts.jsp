<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your receipts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<div class="container mt-3 mx-auto">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">receipt_id</th>
            <th scope="col">Product Name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Count</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.receipts}" var="receipt">
            <tr>
                <th scope="row">${receipt.id}</th>
                <td>${receipt.product.name}</td>
                <td>${receipt.product.description}</td>
                <td>${receipt.product.price}$</td>
                <td>${receipt.count}</td>
            </tr>
        </c:forEach>
        <tr class="table-success">
            <th scope="row">Total price</th>
            <th>${requestScope.total}$</th>
        </tr>
        </tbody>
    </table>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/store-products" role="button">Add more products</a>
    <a class="btn btn-dark" href="" role="button">Log out</a>
</div>
</body>
</html>
