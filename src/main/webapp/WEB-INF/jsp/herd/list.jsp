<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<div id="herdListPage">
  <div id="listTitle">
    <h2>Herd List</h2>
  </div>
  <div id="listContainer">
    <%-- <div> --%>
      <%-- <table id="herdList"> --%>
      <table id="listTable">
        <thead>
          <tr class=listTableRow>
            <td class="listTableData"></td>
            <td class="listTableData">Primary Id</td>
            <td class="listTableData">Second Id</td>
            <td class="listTableData">Sex</td>
            <td class="listTableData">Status</td>
          </tr>
        </thead>
        <tbody id="listPrintOut">
          <c:forEach items="${herd}" var="herdList">
            <tr class=listTableRow>
              <td class="listTableData">
                <a href="./herdinfo?cowId=${herdList.animalId.animalId1}" id="tableButton"><button >Edit</button></a>&emsp;
              </td>
              <td class="listTableData">${herdList.animalId.animalId1}</td>
              <td class="listTableData">${herdList.animalId.animalId2}</td>
              <td class="listTableData">${herdList.animalId.animalType}</td>
              <td class="listTableData">${herdList.animalId.herdStatus}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    <%-- </div> --%>
    <div id="listFooter">
    </div>
</div>
</section>
<div id="sideContent">

</div>
<jsp:include page="../includes/footer.jsp"/>