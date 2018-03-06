
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>

    <style>

        .h1 {
            color: black;
            font-size: 70px;
            font-family: "Times New Roman";
            text-align: center;
        }

        .table {
            margin: auto;
            border-width: 1px;
            border-style: solid;

        }

        .table th {
            text-align: center;
            font-family: Arial, sans-serif;
            font-weight: bold ;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: white;
            background-color: #f16731;
        }

        .table td {
            text-align: center;
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
        }

    </style>
</head>
<body>

<h3> <a href="index.html">Home</a></h3>

<h1 class="h1">Meals</h1>



<table class="table">
    <thead>
    <th width="200">Дата/время</th>
    <th width="200">Описание</th>
    <th width="200">Калорий</th>
    </thead>


<tr>
    <tbody>
    <c:set var="meals" value="${requestScope.meals}" />
    <c:set var="formatter" value="${requestScope.formatter}" />

    <c:forEach items="${meals}" var="meal">
    <tr style="color: ${meal.exceed ? '#d63104' : '#00ab14'}">
        <td><c:out value="${meal.dateTime.format(formatter)}"/></td>
        <td><c:out value="${meal.description}"/></td>
        <td><c:out value="${meal.calories}"/></td>
    </c:forEach>
    <tbody/>
</tr>
</table>



</body>
</html>



