<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form action="/login/loginSubmit" method="post" class="loginPageStyle">
    <div class="lTitle">
        <h1 style="font-size: xx-large">Login Page</h1><br>
    </div>
    <div class="lUserName">
        <label for="userNameID" style="font-size: x-large">Username:</label>
        <input id="userNameID" type="text" name="username" style="width: 30%; height:3vh; border-radius: 10px;"><br>
    </div>
    <div class="lPassword">
        <label for="passwordID" style="font-size: x-large">Password:</label>
        <input id="passwordID" type="text" name="password" style="width: 30%; height:3vh; border-radius: 10px;">
    </div>
    <div class="lSubmit">
        <button type="submit"><strong>Login</strong></button>
    </div>

</form>
</section>
<div id="sideContent">


</div>

<%--<%@include file="../../../pub/html/footer.html" %>--%>
<jsp:include page="../includes/footer.jsp"/>