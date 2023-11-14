<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${tecnicos}" var="tecnico">
               <tr>
                  <th scope="row">${tecnico.id}</th>
                  <td>${tecnico.nome}</td>
                  <td>${tecnico.idade}</td>
                  <td>${tecnico.nacionalidade}</td>
               </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>