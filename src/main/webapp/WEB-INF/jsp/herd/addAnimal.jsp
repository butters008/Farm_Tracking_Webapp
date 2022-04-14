<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<%--<jsp:include page="include/header.jsp"/>--%>
<section id="mainContent">
<%--<div id="addAnimalPage">--%>
<%--<form  action="/herd/submitAnimal" method="get">--%>
<form id="addAnimalPage"  action="/herd/submitAnimal" method="get">
    <div class="aTitle">
        <h1>Add Parent to Herd</h1>
    </div>


    <div class="aType">

<%--        <label for="animalType">Animal Type</label><br>--%>
<%--        Cow<input type="radio" id="cowRadio" name="animalType" value="cow">--%>
<%--        Bull<input type="radio" id="bullRadio" name="animalType" value="bull">--%>

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
        <label for="animalStatus">Animal Type</label><br>
        <div id="animalStatus">
            Active<input type="radio" name="herdStatus" value="Active">
            Sold<input type="radio" name="herdStatus" value="Sold">
            Butchered<input type="radio" name="herdStatus" value="Butchered">
            Dead<input type="radio" name="herdStatus" value="Dead">
        </div><br>
        <label for="broughtFrom">Bought From</label>
        <input type="text" name="boughtFrom" id="broughtFrom"><br>
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
    <form action="" method="get">
        <button>Add Cow</button><br>
        <button>Add Bull</button><br>
        <button>Add Calf</button><br>
    </form>
</div>

<%--<%@include file="../../../pub/html/footer.html" %>--%>
<jsp:include page="../includes/footer.jsp"/>
