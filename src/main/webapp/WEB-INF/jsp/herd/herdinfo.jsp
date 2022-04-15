<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">

    <form id="addAnimalPage"  action="/herd/updateAnimal/${herd.id}" method="post">
        <div class="aTitle">
            <h1>Animal Information</h1>
            <input type="text" name="id" id="id" value="${herd.id}" style="pointer-events: none;" disabled hidden>
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
                    </tr>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <input type="text" id="parentType" name="animalType" value="${herd.animalType}" disabled>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="aPic">
            srkfugjhb
        </div>

        <div class="aID">
            <label for="animalId1">Animal ID #1</label><br>
            <input type="text" name="animalId1" id="animalId1" value="${herd.animalId1}"><br>

            <label for="animalId2">Animal ID #2</label><br>
            <input type="text" name="animalId2" id="animalId2" value="${herd.animalId2}"><br>
        </div>

        <%-- They will have to recheck, just incase the status of animal changed --%>
        <%-- TODO: Have a check to have so it auto fills what its is --%>
        <div class="aStatus">
            <label for="breed">Animal Breed</label><br>
            <input type="text" name="breed" id="breed" value="${herd.breed}"><br>
            <label for="animalStatus">Animal Type</label><br>
            <div id="animalStatus">
                Active<input type="radio" name="herdStatus" value="Active">
                Sold<input type="radio" name="herdStatus" value="Sold">
                Butchered<input type="radio" name="herdStatus" value="Butchered">
                Dead<input type="radio" name="herdStatus" value="Dead">
            </div><br>
            <label for="boughtFrom">Bought From</label>
            <input type="text" name="boughtFrom" id="boughtFrom" value="${herd.boughtFrom}"><br>
        </div>

        <div class="aDates">
            <div class="naturalDate">
                <label for="dateOfBirth">Date of Birth</label>
                <input type="date" name="dateOfBirth" id="dateOfBirth" value="${herd.dateOfBirth}"><br>
                <label for="dateOfDeath">Date of Death</label>
                <input type="date" name="dateOfDeath" id="dateOfDeath" value="${cow.dateOfDeath}"><br>
            </div>
            <div class="otherDate">
                <label for="boughtDate">Date Bought</label><br>
                <input type="date" name="boughtDate" id="boughtDate" value="${cow.boughtDate}"><br>
            </div>
        </div>

        <div class="aSubmit">
            <a href="#delete" id="deleteBtn"><button type="button">Delete</button></a>
            <button type="submit">Save</button>
        </div>


    </form>
</section>
<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>


<jsp:include page="../includes/footer.jsp"/>
<%--<%@include file="../../../pub/html/footer.html" %>--%>
