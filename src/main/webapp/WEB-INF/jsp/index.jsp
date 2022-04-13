<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../pub/html/header.html" %>--%>
<jsp:include page="includes/header.jsp"/>
<section id="mainContent">
    <h1>Hello</h1>
    <ul>
        ${user}
        <%--    <c:forEach items="${userList}" var="user">--%>
        <%--        <li>${user.firstName}</li>--%>
        <%--    </c:forEach>--%>
    </ul>
</section>
<div id="sideContent">
<%--    <button>Add Animal</button>--%>

</div>


<jsp:include page="includes/footer.jsp"/>
<%--<%@include file="../../pub/html/footer.html" %>--%>