<%@ page import="ru.javawebinar.topjava.util.DateTimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
        section {
            padding-bottom: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <a href="meals?action=create">Add Meal</a>
</section>
<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Operation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="mealTo" items="${mealTos}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr>
            <td class="${mealTo.excess ? 'exceeded':'normal'}">${DateTimeUtil.formatDate(mealTo.getDateTime())}</td>
            <td class="${mealTo.excess ? 'exceeded':'normal'}">${mealTo.description}</td>
            <td class="${mealTo.excess ? 'exceeded':'normal'}">${mealTo.calories}</td>
            <td><a href="meals?action=update&id=${mealTo.id}">Update</a> </td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
