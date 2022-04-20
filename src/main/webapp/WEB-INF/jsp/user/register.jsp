<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form action="/user/registerSubmit" method="post" class="NewUserFormPage">
    <div class="rTitle">
        <div class="rTitle">New User - Sign Up</div>
    </div>
    <div class="rBInfo">
        <label for="firstName">First Name</label><br>
        <input type="text" name="firstName" id="firstName" onfocusout="validateFirstName()"/>
        <div id="errorFname"></div><br>

        <label for="lastName">Last Name</label><br>
        <input type="text" name="lastName" id="lastName" onfocusout="validateLastName()"/>
        <div id="errorLname"></div><br>

        <label for="email">Email</label><br>
        <input type="email" name="email" id="email" onfocusout="validateEmail()"/>
        <div id="errorEmail"></div><br>

        <label for="password">Password</label><br>
        <input type="password" name="password" id="password" onfocusout="validatePassword()"/>
        <span id="errorPassword"></span><br>

        <label for="passwordConfirm">Password Confirm</label><br>
        <input type="password" name="password" id="passwordConfirm" onfocusout="validateConfirmPassword()"/>
        <span id="errorConfirmPassword"></span><br>
    </div>
    <div class="rSub">
        <button type="submit" id="submit">Submit</button>
    </div>
</form>

<script src="../pub/js/UserForm.js"></script>

</section>

<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>

<jsp:include page="../includes/footer.jsp"/>
<%--<%@include file="../../../pub/html/footer.html" %>--%>