<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form action="/user/registerSubmit" method="post" class="NewUserFormPage">
    <div class="rTitle">
        <div class="rTitle">New User - Sign Up</div>
    </div>
    <div class="rBInfo">
        <label for="firstName">First Name</label><br>
        <input type="text" name="firstName" id="firstName" onfocusout="validateFirstName()" value="${form.firstName}"/>
<%--        <div id="errorFname"></div><br>--%>
        <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label for="lastName">Last Name</label><br>
        <input type="text" name="lastName" id="lastName" onfocusout="validateLastName()" value="${form.lastName}"/>
<%--        <div id="errorLname"></div><br>--%>
        <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label for="email">Email</label><br>
        <input type="email" name="email" id="email" onfocusout="validateEmail()" value="${form.email}"/>
<%--        <div id="errorEmail"></div><br>--%>
        <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label for="password">Password</label><br>
        <input type="password" name="password" id="password" onfocusout="validatePassword()" value="${form.password}"/>
<%--        <span id="errorPassword"></span><br>--%>
        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>

        <label for="passwordConfirm">Password Confirm</label><br>
        <input type="password" name="passwordConfirm" id="passwordConfirm" onfocusout="validateConfirmPassword()" value="${form.passwordConfirm}"/>
<%--        <span id="errorConfirmPassword"></span><br>--%>
        <c:forEach items='${bindingResult.getFieldErrors("passwordConfirm")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    </div>
    <div class="rSub">
        <button type="submit" id="submit">Submit</button>
    </div>
</form>

<%--<script src="../pub/js/UserForm.js"></script>--%>

</section>

<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>

<jsp:include page="../includes/footer.jsp"/>
<%--<%@include file="../../../pub/html/footer.html" %>--%>