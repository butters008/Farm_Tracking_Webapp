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
            <thead>
                <tr>
                    <th id="headStart"></th>
                    <th>Animal Id-1</th>
                    <th>Animal Id-2</th>
                    <th>Animal Type</th>
                    <th id="End">Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${herd}" var="herdList">
                    <tr>
                        <td>
                            <a href="./herdinfo?cowId=${herdList.animalId.animalId1}"><button>Edit</button></a>&emsp;
<%--                            <button style="background-color: var(--attentionColor)">Preview</button>--%>
                        </td>
                        <td>${herdList.animalId.animalId1}</td>
                        <td>${herdList.animalId.animalId2}</td>
                        <td>${herdList.animalId.animalType}</td>
                        <td>${herdList.animalId.herdStatus}</td>
                    </tr>
                </c:forEach>
            </tbody>
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