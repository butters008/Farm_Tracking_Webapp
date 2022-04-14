<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<div id="herdListPage">
    <div id="listTitle">
        <h2>Herd List</h2>
    </div>
    <div id="listTable">
        <table id="herdList">
            <tr>
                <th>Animal Id-1</th>
                <th>Animal Id-2</th>
                <th>Animal Type</th>
            </tr>
            <c:forEach items="${cows}" var="cow">
                <tr>
                    <td><a href="./herdinfo?cowId=${cow.animalId1}">${cow.animalId1}</a></td>
                    <td>${cow.animalId2}</td>
                    <td>${cow.animalType}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="listFooter">
        <button>next page</button>
    </div>
</div>



</section>
<div id="sideContent">
    <button>Add Cow</button><br>
    <button>Add Bull</button><br>
    <button>Add Calf</button><br>

</div>

<%--<%@include file="../../../pub/html/footer.html" %>--%>
<jsp:include page="../includes/footer.jsp"/>