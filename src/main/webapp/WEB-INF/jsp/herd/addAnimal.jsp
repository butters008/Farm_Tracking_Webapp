<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form id="addAnimalPage"  action="/herd/submitAnimal" method="post">
    <div class="aTitle">
        <input type="hidden" name="id" value="${form.id}">
        <h1>Add Parent to Herd</h1>
    </div>
    <div class="aType">
        <div id="animalType">
            <h2>Animal Type</h2>
            <table>
                <tbody>
                <tr>
                    <td>Cow</td>
                    <td>Bull</td>
                </tr>
                <tr>
                    <td><input type="radio" id="cowRadio" name="animalType" value="cow"></td>
                    <td><input type="radio" id="bullRadio" name="animalType" value="bull"></td>
                    <c:forEach items='${bindingResult.getFieldErrors("animalType")}' var="error">
                        <div style="color: red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="aPic">
        srkfugjhb
    </div>

    <div class="aID">
        <label for="animalId1">Animal ID #1</label><br>
        <input type="text" name="animalId1" id="animalId1"><br>


        <label for="animalId2">Animal ID #2</label><br>
        <input type="text" name="animalId2" id="animalId2"><br>


    </div>

    <div class="aStatus">
        <label for="breed">Animal Breed</label><br>
        <input type="text" name="breed" id="breed"><br>
        <c:forEach items='${bindingResult.getFieldErrors("breed")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>
        <label for="animalStatus">Animal Type</label><br>
        <div id="animalStatus">
            Active<input type="radio" name="herdStatus" value="Active">
            Sold<input type="radio" name="herdStatus" value="Sold">
            Butchered<input type="radio" name="herdStatus" value="Butchered">
            Dead<input type="radio" name="herdStatus" value="Dead">
        </div><br>
        <c:forEach items='${bindingResult.getFieldErrors("herdStatus")}' var="error">
            <div style="color: red;">${error.getDefaultMessage()}</div>
        </c:forEach>
        <label for="boughtFrom">Bought From</label>
        <input type="text" name="boughtFrom" id="boughtFrom"><br>
    </div>

    <div class="aDates">
        <div class="naturalDate">
            <label for="dateOfBirth">Date of Birth</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth"><br>
            <label for="dateOfDeath">Date of Death</label>
            <input type="date" name="dateOfDeath" id="dateOfDeath"><br>
        </div>
        <div class="otherDate">
            <label for="boughtDate">Date Bought</label><br>
            <input type="date" name="boughtDate" id="boughtDate"><br>
        </div>
    </div>

    <div class="aSubmit">
        <button type="submit">Submit</button>
    </div>


</form>
<%--</div>--%>
</section>
<div id="sideContent">

</div>


<jsp:include page="../includes/footer.jsp"/>
