<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<c:if test="${calf}">
<form class="addCalfPage" action="herd/editCalf" method="post">
    <div class="calfTitle">
        <h2>Test Calf Exist</h2>
    </div>
</c:if>
<c:if test="${empty calf}">
<form class="addCalfPage" action="/herd/addNewCalf" method="post">
    <div class="calfTitle">
        <h2>Test Calf NEW CALF</h2>
    </div>
</c:if>
    <div class="calfBasicInfo">
        <h3>Basic Calf Information (required info)</h3>
        <div class="calfIDS">
            <table>
                <thead>
                    <tr>
                        <th><label for="calfId1">Calf Id: 1</label></th>
                        <th><label for="calfId2">Calf Id: 2 (opt)</label></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" name="calfId1" id="calfId1"></td>
                        <td><input type="text" name="calfId2" id="calfId2"></td>
                    </tr>
                    <tr>
                        <td><label for="breed">Breed</label></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="breed" id="breed"></td>
                    </tr>
                </tbody>
            </table>

        </div>
        <label for="calfSex">Calf Sex</label>
        <div id="calfSex">
            <table>
                <thead>
                    <tr>
                        <th>Male</th>
                        <th>Female</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="radio" name="calfSex" value="MALE"></td>
                        <td><input type="radio" name="calfSex" value="FEMALE"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="weightAndParents">
            <table>
                <tbody>
                    <tr>
                        <td><label for="dateOfBirth">Date of Birth</label></td>
                        <td><label for="mother">Mother</label></td>

                    </tr>
                    <tr>
                        <td><input type="date" name="dateOfBirth" id="dateOfBirth"></td>
<%--                        <td><input type="text" name="mother" id="mother"></td>--%>
                        <td>
                            <select id="mother" name="mother" >
                                <c:forEach items="${cows}" var="cow">
                                    <option value="${cow.id}">${cow.animalId1}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="birthWeight">Birth Weight</label></td>
                        <td><label for="father">Father</label></td>
                    </tr>
                    <tr>
                        <td><input type="number" name="birthWeight" id="birthWeight"></td>
                        <td>
                            <select id="father" name="father" >
                                <c:forEach items="${bulls}" var="bull">
                                    <option value="${bull.id}">${bull.animalId1}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div><br><br><br><br>

    </div>
    <div class="calfImage">
tjp
    </div>
    <div class="calfExtraInfo">
tjpio
    </div>
    <div class="calfSubmit">
        <c:if test="${empty calf}">
            <button><strong>Save</strong></button>
        </c:if>
    </div>


</form>
</section>
<div id="sideContent">

</div>


<jsp:include page="../includes/footer.jsp"/>