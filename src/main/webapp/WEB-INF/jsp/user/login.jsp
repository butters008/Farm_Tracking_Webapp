<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../pub/html/header.html" %>
<section id="mainContent">
    <h1>Login Page</h1>
    <form action="registerSubmit.html">
        <div class="container ">
            <div class="row d-flex justify-content-center customFormContainerStyle">
                <div class="col col-4 text-center">
                    <label for="email"><strong>email</strong></label>
                    <input class="form-control" type="text" name="email" id="email" placeholder="John@john.com">
                    <div id="errorEmail"></div>
                </div>
            </div>
            <div class="row d-flex justify-content-center customFormContainerStyle">
                <div class="col col-4 text-center">
                    <label for="password"><strong>Password</strong></label>
                    <input class="form-control" onfocusout="validatePassword()" type="password" name="password" id="password" placeholder="password">
                    <span id="errorPassword"></span>
                </div>
            </div>
        </div><br>
        <div class="col-1 mx-auto">
            <button class="btn btn=lg btn-primary btn-block" type="submit" id="login">submit</button>
        </div>

    </form>
</section>
<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>



<%@include file="../../../pub/html/footer.html" %>