<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent" style="text-align: center;">
<%--This is for a editing any parent in herd--%>
<c:if test="${not empty herd.id}">
<form id="addAnimalPage" name="herdForm"  action="/herd/updateAnimal/${herd.id}" method="post">
</c:if>
<%--This is for adding a parent in the herd--%>
<c:if test="${empty herd.id}">
<form id="addAnimalPage" name="herdForm"  action="/herd/submitAnimal" method="post">
</c:if>
    <div class="aTitle">
        <h1>Animal Information</h1>
        <input type="text" name="id" id="id" value="${herd.id}" style="pointer-events: none;" disabled hidden>
    </div>
    <div class="aType">
        <table class="herdTableForm">
            <tbody>
            <tr><h3>Basic Information</h3></tr>
            <tr>
                <td>Sex</td>
                <td>Raised - Calf ID (opt)</td>
            </tr>
            <tr>
                <td>
                    <select name="animalType" class="herdDropDownInput" id="aTypeDropDown">
                    <c:if test="${not empty herd.id}">
                        <option value="${herd.animalType}" selected>${herd.animalType}</option>
                        <option value="cow">Cow</option>
                        <option value="bull">Bull</option>
                    </c:if>
                    <c:if test="${empty herd.id}">
                        <option value="cow" selected>Cow</option>
                        <option value="bull">Bull</option>
                    </c:if>
                    </select>
                </td>
                <td>
                    <input type="text" id="BornAndRaised" placeholder="Required if Kept">
                </td>
            </tr>
            </tbody>
        </table>

    </div>

    <div class="aPic">
        <table class="herdTableForm">
            <tr><h3>Herd Other Information</h3></tr>
            <tr>
                <td><label for="boughtFrom">Bought From</label></td>
                <td><label for="boughtDate">Date Bought</label></td>
            </tr>
            <tr>
                <td><input type="text" name="boughtFrom" id="boughtFrom" value="${herd.boughtFrom}"></td>
                <td><input type="date" name="boughtDate" id="boughtDate" value="${herd.boughtDate}"></td>
            </tr>
        </table>

        <br>
    </div>

    <%-- This was refactored for required information --%>
    <div class="aID">
        <table class="herdTableForm">
            <tbody>
                <tr><h3>Required Information</h3></tr>
                <tr>
                    <td><label for="animalId1">ID #1</label></td>
                    <td><label for="animalId2">ID #2 (opt)</label></td>
                </tr>
                <tr>
                    <td><input type="text" name="animalId1" id="animalId1" onfocusout="validateAnimalId1()" value="${herd.animalId1}"></td>
                    <td><input type="text" name="animalId2" id="animalId2" value="${herd.animalId2}"></td>
                </tr>
                <tr class="errorJSChecking">
                    <!-- This row will be used for error checking -->
                    <td><div id="errorAId1" style="color: red;"><c:forEach items='${bindingResult.getFieldErrors("animalId1")}' var="error">${error.getDefaultMessage()}</c:forEach></div></td>
                </tr>
                <tr>
                    <td><label for="breed">Animal Breed</label></td>
                    <td><label for="animalStatus">Animal Status</label></td>
                </tr>
                <tr>
                    <td><input type="text" name="breed" id="breed" onfocusout="validateBreed()" value="${herd.breed}"></td>
                    <td>
                        <div id="animalStatus">
                            <select name="herdStatus" class="herdDropDownInput">
                                <option value="${herd.herdStatus}" selected>${herd.herdStatus}</option>
                                <option value="Active">Active</option>
                                <option value="Sold">Sold</option>
                                <option value="Butchered">Butchered</option>
                                <option value="Dead">Dead</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr class="errorJSChecking">
                    <!-- This row will be used for error checking -->
                    <td><div id="errorBreed" style="color: red;"><c:forEach items='${bindingResult.getFieldErrors("breed")}' var="error">${error.getDefaultMessage()}</c:forEach></div></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- This is going to be used for holding the PICTURE of the cow -->
    <div class="aStatus">
        <c:if test="${not empty herd.id}">

        </c:if>
        <c:if test="${empty herd.id}">

        </c:if>
    </div>

    <div class="aDates">
        <table class="herdTableForm">
            <tbody>
                <tr><h3>Dates of Animals</h3></tr>
                <tr>
                    <td><label for="dateOfBirth">Date of Birth</label></td>
                    <td><label for="dateOfDeath">Date of Death</label></td>
                </tr>
                <tr>
                    <td><input type="date" name="dateOfBirth" id="dateOfBirth" value="${herd.dateOfBirth}"></td>
                    <td><input type="date" name="dateOfDeath" id="dateOfDeath" value="${herd.dateOfDeath}"></td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="aSubmit">
        <c:if test="${not empty herd.id}">
            <a href="./delete/${herd.id}" id="deleteBtn"><button type="button">Delete</button></a>
            <button type="submit" id="button">Save</button>
        </c:if>

        <c:if test="${empty herd.id}">
            <button type="submit" id="button">Submit</button>
        </c:if>
    </div>


</form>
<script src="../pub/js/CowForm.js"></script>
</section>
<div id="sideContent">
    <div class="CalfListOnHerd">
        <h3>Calf List</h3>
        <table style="background-color: rgba(129,129,129,0.47); border-radius: 15px;">
            <thead>
                <tr>
                    <th></th>
                    <th>Calf ID (#1)</th>
                    <th>Sex</th>
                    <th>Birth Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${calves}" var="calfList">
                <tr>
                    <td><a href="./calfUpdate?calfId=${calfList.calf.id}"><button>Edit</button></a></td>
                    <td>${calfList.calf.calfId1}</td>
                    <td>${calfList.calf.calfSex}</td>
                    <td>${calfList.calf.dateOfBirth}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<jsp:include page="../includes/footer.jsp"/>

