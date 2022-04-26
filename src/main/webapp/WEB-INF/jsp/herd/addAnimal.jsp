<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
<form id="addAnimalPage"  action="/herd/submitAnimal" method="post" enctype="multipart/form-data">
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
                    <td><input type="radio" id="cowRadio" name="animalType" value="cow" checked></td>
                    <td><input type="radio" id="bullRadio" name="animalType" value="bull" onfocusout="validateAnimalType()"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="aPic">
    </div>

    <div class="aID">
        <label for="animalId1">Animal ID #1</label><br>
        <input type="text" name="animalId1" id="animalId1" onfocusout="validateAnimalId1()"><br>
        <div id="errorAId1"></div><br>

        <label for="animalId2">Animal ID #2</label><br>
        <input type="text" name="animalId2" id="animalId2"><br>


    </div>

    <div class="aStatus">
        <label for="breed">Animal Breed</label><br>
        <input type="text" name="breed" id="breed" onfocusout="validateBreed()"><br>
        <div id="errorBreed"></div><br>
        <label for="animalStatus">Animal Status</label><br>
        <div id="animalStatus">
            Active<input type="radio" name="herdStatus" value="Active" checked>
            Sold<input type="radio" name="herdStatus" value="Sold">
            Butchered<input type="radio" name="herdStatus" value="Butchered">
            Dead<input type="radio" name="herdStatus" value="Dead">
        </div><br>

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
        <button type="submit" id="button" onclick="lastCheck()">Submit</button>
    </div>


</form>
<script src="../pub/js/CowForm.js"></script>
</section>
<div id="sideContent">

</div>


<jsp:include page="../includes/footer.jsp"/>
