<%@include file="../../../pub/html/header.html" %>
<section id="mainContent">
    <h1>Login Page</h1>
    <form action="/login/loginSubmit" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="text" name="password">

        <br>
        <br>
        <button type="submit">Login</button>
    </form>
</section>
<div id="sideContent">


</div>

<%@include file="../../../pub/html/footer.html" %>