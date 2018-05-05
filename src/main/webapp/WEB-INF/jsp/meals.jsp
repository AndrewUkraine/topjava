<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">

    <h3><spring:message code="meal.title"/></h3>

    <form method="post" action="meals/filter">

        <dl class="jumbotron">

            <ul class="list-group">

            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>

            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>

            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>


            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>

            </ul>
        </dl>

        <button type="submit" class="btn btn-success"><spring:message code="meal.filter"/></button>
    </form>
    <hr>

    <button class="btn btn-primary" onclick="add()">
                   <span class="fa fa-plus"></span>
                    <spring:message code="common.add"/>
                </button>


    <table class="table table-striped" id="datatable">
    <hr>
    <%--<table border="1" cellpadding="8" cellspacing="0">--%>
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>


        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                            <tr data-mealExceed="${meal.exceed}">
                                <td>
                                        ${fn:formatDateTime(meal.dateTime)}
                                </td>
                                <td>${meal.description}</td>
                                <td>${meal.calories}</td>
                                <td><a><span class="fa fa-pencil"></span></a></td>
                                <td><a onclick="deleteRow(${meal.id})"><span class="fa fa-remove"></span></a></td>
                            </tr>

        </c:forEach>
    </table>

    </div>



<jsp:include page="fragments/footer.jsp"/>
</body>
</html>