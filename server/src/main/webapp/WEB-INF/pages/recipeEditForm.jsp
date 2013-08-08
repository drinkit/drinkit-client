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

<body class="k-content">
<div id="main">
    <%--<form:form action="manager/saveRecipe">--%>

    <%--<form:input path="name"/>--%>
    <%--<form:textarea path="description"/>--%>
    <%--<form:select path="cocktailType"/>--%>

    <%--</form:form>--%>

    <form:form action="saveRecipe.html" method="POST">
        <ul class="forms">
            <li><label>Name: </label></li>
            <li><form:input path="name" type="text" class="k-textbox"/></li>
            <li><label>Description:</label></li>
            <li><form:textarea path="description" class="k-textbox"/></li>
            <li><label>CocktailType:</label></li>
            <li><form:select id="cocktailType" path="cocktailType" items="${cocktailTypes}" itemLabel="name"/></li>
            <li><input type="submit" name="Save"/></li>
        </ul>
    </form:form>
    <style scoped>
        .forms {
            float: left;
        }

        .forms li {
            margin-bottom: 5px;
            list-style: none;
        }

        .forms li > * {
            width: 200px;
        }
    </style>
</div>

<script>
    $("#cocktailType").kendoDropDownList();
</script>

</body>
</html>