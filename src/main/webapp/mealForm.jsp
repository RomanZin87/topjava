<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<section>
    <h3><%=request.getParameter("action").equalsIgnoreCase("create") ?  "Add new" : "Edit"%> meal</h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><label><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></label></dd>
            <dt>Description:</dt>
            <dd><label><input type="text" value="${meal.description}" name="description"></label></dd>
            <dt>Calories:</dt>
            <dd><label><input type="number" value="${meal.calories}" name="calories"></label></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
