<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<%--    ${calf}--%>
<c:if test="${not empty calf.id}">
<form class="addCalfPage" action="/herd/UpdateCalf?calfId=${calf.id}" method="post">
  <div class="calfTitle">
    <h2>Update Calf Information</h2>
  </div>
</c:if>
<c:if test="${empty calf.id}">
<form class="addCalfPage" action="/herd/addNewCalf" method="post">
  <div class="calfTitle">
    <h2>Enter New Calf Information</h2>
  </div>
</c:if>
  <div class="calfBasicInfo">
    <h3>Required Information</h3>
    <div class="calfIDS">
      <table>
        <thead>
          <tr>
            <th>
              <label for="calfId1">Calf Id: 1</label>
            </th>
            <th>
              <label for="calfId2">Calf Id: 2 (opt)</label>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input type="text" name="calfId1" id="calfId1" value="${calf.calfId1}">
            </td>
            <td>
              <input type="text" name="calfId2" id="calfId2" value="${calf.calfId2}">
            </td>
          </tr>
        <c:forEach items='${bindingResult.getFieldErrors("calfId1")}' var="error">
          <tr>
            <td>
              <div style="color: red;">
                ${error.getDefaultMessage()}
              </div>
            </td>
          </tr>
        </c:forEach>
          <tr>
            <td>
              <label for="breed">Breed</label>
            </td>
            <td>
              <label for="calfSex">Calf Sex</label>
            </td>
          </tr>
          <tr>
            <td>
              <input type="text" name="breed" id="breed" value="${calf.breed}">
            </td>
            <td>
            <c:if test="${not empty calf}">
              <select id="calfSex" name="calfSex" value="${parentCalf.calf}" class="herdDropDownInput" id="calfDropDown">
                <option value="${calf.calfSex}" selected>${calf.calfSex}</option>
                <option value="MALE" >Male</option>
                <option value="FEMALE" >Female</option>
              </select>
            </c:if>
            <c:if test="${empty calf}">
              <select id="mother" name="calfSex" value="${parentCalf.calf}" class="herdDropDownInput" id="calfDropDown">
                <option value="MALE" selected>Male</option>
                <option value="FEMALE" >Female</option>
              </select>
            </c:if>
            </td>
          </tr>
          <tr>
          <c:forEach items='${bindingResult.getFieldErrors("breed")}' var="error">
            <td>
              <div style="color: red;">
                ${error.getDefaultMessage()}
              </div>
            </td>
          </c:forEach>
          <c:forEach items='${bindingResult.getFieldErrors("calfSex")}' var="error">
            <td>
              <div style="color: red;">
                ${error.getDefaultMessage()}
              </div>
            </td>
          </c:forEach>
          </tr>
        </tbody>
      </table>
    </div>

    <%-- For right now, we are going to do two different inputs --%>
    <div class="weightAndParents">
      <table>
        <tbody>
          <tr>
            <td>
              <label for="dateOfBirth">Date of Birth</label>
            </td>
            <td>
              <label for="mother">Mother</label>
            </td>
          </tr>
          <tr>
            <td>
              <input type="date" name="dateOfBirth" id="dateOfBirth" value="${calf.dateOfBirth}">
            </td>
            <td>
              <select id="mother" name="mother">
              <c:if test="${not empty parentCalf.cow}">
                <option value="${parentCalf.cow.id}" selected>${parentCalf.cow.animalId1}</option>
              </c:if>
              <c:forEach items="${cows}" var="cow">
                <option value="${cow.id}">${cow.animalId1}</option>
              </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td>
              <label for="birthWeight">Birth Weight*</label>
            </td>
            <td>
              <label for="father">Father</label>
            </td>
          </tr>
          <tr>
            <td>
              <input type="number" name="birthWeight" id="birthWeight" value="${calf.birthWeight}">
            </td>
            <td>
              <select id="father" name="father" >
              <c:if test="${not empty parentCalf.bull}">
                <option value="${parentCalf.bull.id}" selected>${parentCalf.bull.animalId1}</option>
              </c:if>
              <c:forEach items="${bulls}" var="bull">
                <option value="${bull.id}">${bull.animalId1}</option>
              </c:forEach>
              </select>
            </td>
          </tr>
          <c:forEach items='${bindingResult.getFieldErrors("birthWeight")}' var="error">
          <tr>
            <td>
              <div style="color: red;">
                ${error.getDefaultMessage()}
              </div>
            </td>
          </tr>
          </c:forEach>
          <c:forEach items='${bindingResult.getFieldErrors("dateOfBirth")}' var="error">
          <tr>
            <td>
              <div style="color: red;">
                ${error.getDefaultMessage()}
              </div>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

<br><br><br><br>

  <div class="calfImage">
<%--    <button onclick="working2()" type="button">JavaScript Function 2</button>--%>
  </div>
  <div class="calfExtraInfo">
    <table>
      <tbody style="text-align: center">
        <tr>
          <td colspan="2">
            <h3>Additional Information</h3>
          </td>
        </tr>
        <tr>
          <td>
            <label>Wean Date</label>
          </td>
          <td>
            <label>Wean Weight</label>
          </td>
        </tr>
        <tr>
          <td>
            <input style="width: 90%;" type="date" name="weanDate" id="weanDate" value="${calf.weanDate}">
          </td>
          <td>
            <input type="number" name="weanWeight" id="weanWeight" value="${calf.weanWeight}">
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="calfSubmit">
  <c:if test="${empty calf}">
    <button><strong>Save</strong></button>
  </c:if>
  <c:if test="${not empty calf}">
  <%-- TODO: Finish delete function --%>
    <a href="/herd/deleteCalf/${calf.id}">
      <button type="button"><strong>Delete</strong></button>
    </a>
    <button><strong>Save</strong></button>
  </c:if>
  </div>
</form>
</section>

<div id="sideContent">
</div>


<jsp:include page="../includes/footer.jsp"/>