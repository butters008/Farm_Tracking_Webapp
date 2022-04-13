<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form action="/user/registerSubmit" method="post">


    <label for="animalType">Animal Type</label>
    <div id="animalType">
        Cow<input type="checkbox" name="checkbox" value="cow">
        Bull<input type="checkbox" name="checkbox" value="bull">
        Bull<input type="checkbox" name="checkbox" value="calf">
    </div>

    <label for="animalId1">Animal ID #1</label>
    <input type="text" name="animalId1" id="animalId1"><br>

    <label for="animalId2">Animal ID #2</label>
    <input type="text" name="animalId2" id="animalId2"><br>






    <br><br>
    <button type="submit">Submit</button>



<%--This was error handling when it came to the form--%>
<%--    <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">--%>
<%--        <div style="color: red;">${error.getDefaultMessage()}</div>--%>
<%--    </c:forEach>--%>
<%--First Name<input type="text" name="firstName" id="firstNameId" value="${form.firstName}"><br>--%>
<%--<c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">--%>
<%--    <div style="color: red;">${error.getDefaultMessage()}</div>--%>
<%--</c:forEach>--%>

<%--Last Name<input type="text" name="lastName" id="lastNameId" value="${form.lastName}"><br>--%>
<%--<c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">--%>
<%--    <div style="color: red;">${error.getDefaultMessage()}</div>--%>
<%--</c:forEach>--%>

<%--Password<input type="password" name="password" id="passwordId" value="${form.password}"><br>--%>
<%--<c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">--%>
<%--    <div style="color: red;">${error.getDefaultMessage()}</div>--%>
<%--</c:forEach>--%>

<%--This was on top, dont think I need it, will add if needed--%>
<%--<input type="hidden" name="id" value="${form.id}">--%>


</form>

</section>

<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>

<jsp:include page="../includes/footer.jsp"/>
<%--<%@include file="../../../pub/html/footer.html" %>--%>