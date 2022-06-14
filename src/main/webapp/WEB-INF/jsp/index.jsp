<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="includes/header.jsp"/>
<section id="mainContent">
    <sec:authorize access="!isAuthenticated()">
        <div id="noIndex" style="text-align: center">
            <h2>Welcome to Farm Management System</h2><br><br>
            <p>If you would like to use FMS,<br> please sign in or create a new account</p>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div id="userIndex">
            <div class="iTitle">
                <h2>${user.lastName} Farm - Summary</h2>
            </div>
            <div class="iBull">
                <h3>Bull Count</h3>
                <div style="font-size: xx-large">${bullSize}</div>
            </div>
            <div class="iCow">
                <h3>Cow Count</h3>
                <div style="font-size: xx-large">${herdSize}</div>
            </div>
            <div class="iCalf">
                <h3>Calf Count</h3>
                <div style="font-size: xx-large">${calfSize}</div>
            </div>

        </div>
    </sec:authorize>
</section>
<div id="sideContent">

</div>

<jsp:include page="includes/footer.jsp"/>
<%--<%@include file="../../pub/html/footer.html" %>--%>