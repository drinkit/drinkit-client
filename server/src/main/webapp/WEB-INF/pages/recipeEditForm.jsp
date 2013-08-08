<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/resources/styles/kendo.common.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/styles/kendo.black.min.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/kendo.web.min.js"></script>
    <title>Add/Edit Recipe</title>

<body>
<div id="main">
    <%--<form:form action="manager/saveRecipe">--%>

    <%--<form:input path="name"/>--%>
    <%--<form:textarea path="description"/>--%>
    <%--<form:select path="cocktailType"/>--%>

    <%--</form:form>--%>

    <form>
        <li><label>Name:<input type="text" value="${recipe.name}"/></label></li>
        <li><label>Description:<textarea>${recipe.description}</textarea></label></li>
        <li><label>CocktailType:
            <select id="cocktailType">
                <c:forEach items="${cocktailTypes}" var="type">
                    <option>${type.name}</option>
                </c:forEach>
            </select>
        </label></li>

        </ul>

    </form>
</div>

<script>
    $("#cocktailType").kendoDropDownList();
</script>
</body>
</html>