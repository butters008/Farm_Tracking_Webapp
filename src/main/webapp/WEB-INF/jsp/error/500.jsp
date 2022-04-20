<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
    <div class="errorPage">
    <h1 style="text-align: center; font-size: xxx-large;">Error Page</h1>

    <c:if test="${not empty requestUrl}">
        <p style="text-align: center;">${requestUrl}</p>
    </c:if>
    <h3 style="text-align: center; font-size: x-large;">Stack Trace</h3>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <c:if test="${not empty stackTrace}">
        <p>${stackTrace}</p>
    </c:if>
    <h3 style="text-align: center; font-size: x-large;">Root Cause</h3>
    <c:if test="${not empty rootcause}">
        <p style="text-align: center;">${rootcause}</p>
    </c:if>
    <c:if test="${not empty rootTrace}">
        <p style="text-align: center;">${roottrace}</p>
    </c:if>
    </div>
</section>
<div id="sideContent">

</div>
<jsp:include page="../includes/footer.jsp"/>