<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<div id="herdListPage">
    <div id="listTitle">
        <h2>Herd List</h2>
    </div>
    <div id="listTable">
<%--        <div>--%>
<%--        <table id="herdList">--%>
            <table>
            <thead>
                <tr>
                    <td></td>
                    <td>Primary Id</td>
                    <td>Second Id</td>
                    <td>Sex</td>
                    <td>Status</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${herd}" var="herdList">
                    <tr>
                        <td>
                            <a href="./herdinfo?cowId=${herdList.animalId.animalId1}"><button>Edit</button></a>&emsp;
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
    </div>
</div>
</section>
<div id="sideContent">
    <table id="calfList">
        <thead>
        <tr>
            <th id="calfStart"></th>
            <th>Calf Id-1</th>
            <th>Sex</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${calf}" var="calfList">
            <tr>
                <td>
                    <a href="./calfUpdate?calfId=${calfList.calfId.id}"><button>Edit</button></a>&emsp;
                </td>
                <td>${calfList.calfId.calfId1}</td>
                <td>${calfList.calfId.calfSex}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"/>