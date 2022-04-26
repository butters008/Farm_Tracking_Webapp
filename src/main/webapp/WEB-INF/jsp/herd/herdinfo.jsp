<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent" style="text-align: center;">
<form id="addAnimalPage" name="herdForm"  action="/herd/updateAnimal/${herd.id}" method="post">
    <div class="aTitle">
        <h1>Animal Information</h1>
        <input type="text" name="id" id="id" value="${herd.id}" style="pointer-events: none;" disabled hidden>
    </div>
    <div class="aType">
        <div id="animalType">
            <h2>Animal Type</h2>
            <select name="animalType" class="herdDropDownInput" id="aTypeDropDown">
                <option value="${herd.animalType}" selected>${herd.animalType}</option>
                <option value="cow">Cow</option>
                <option value="bull">Bull</option>
            </select>
        </div>
    </div>

    <div class="aPic">

    </div>

    <div class="aID">
        <label for="animalId1">Animal ID #1</label><br>
        <input type="text" name="animalId1" id="animalId1" onfocusout="validateAnimalId1()" value="${herd.animalId1}"><br>
        <div id="errorAId1"></div><br>

        <label for="animalId2">Animal ID #2</label><br>
        <input type="text" name="animalId2" id="animalId2" value="${herd.animalId2}"><br>
    </div>

    <div class="aStatus">
        <label for="breed">Animal Breed</label><br>
        <input type="text" name="breed" id="breed" onfocusout="validateBreed()" value="${herd.breed}"><br>
        <div id="errorBreed"></div>

        <label for="animalStatus">Animal Status</label><br>
        <div id="animalStatus">
            <select name="herdStatus" class="herdDropDownInput">
                <option value="${herd.herdStatus}" selected>${herd.herdStatus}</option>
                <option value="Active">Active</option>
                <option value="Sold">Sold</option>
                <option value="Butchered">Butchered</option>
                <option value="Dead">Dead</option>
            </select>
        </div><br><br>
        <label for="boughtFrom">Bought From</label>
        <input type="text" name="boughtFrom" id="boughtFrom" value="${herd.boughtFrom}"><br>
    </div>

    <div class="aDates">
        <div class="naturalDate">
            <label for="dateOfBirth">Date of Birth</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth" value="${herd.dateOfBirth}"><br>
            <label for="dateOfDeath">Date of Death</label>
            <input type="date" name="dateOfDeath" id="dateOfDeath" value="${herd.dateOfDeath}"><br>
        </div>
        <div class="otherDate">
            <label for="boughtDate">Date Bought</label><br>
            <input type="date" name="boughtDate" id="boughtDate" value="${herd.boughtDate}"><br>
        </div>
    </div>

    <div class="aSubmit">
        <a href="./delete/${herd.id}" id="deleteBtn"><button type="button">Delete</button></a>
        <button type="submit" id="button">Save</button>
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

