<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../pub/html/header.html" %>
<%--<jsp:include page="include/header.jsp"/>--%>
<section id="mainContent">
    <h1>Hello</h1>
    <form action="/herd/submitAnimal" method="get">
        <label for="animalType">Animal Type</label>
            <div id="animalType">
                Cow<input type="radio" id="cowRadio" name="animalType" value="cow">
                Bull<input type="radio" id="bullRadio" name="animalType" value="bull">
<%--                Calf<input type="button" id="calfRadio" name="animalType" value="calf">--%>
            </div>

        <label for="animalId1">Animal ID #1</label>
        <input type="text" name="animalId1" id="animalId1"><br>

        <label for="animalId2">Animal ID #2</label>
        <input type="text" name="animalId2" id="animalId2"><br>

        <label for="animalStatus">Animal Type</label>
        <div id="animalStatus">
            Active<input type="radio" name="herdStatus" value="Active">
            Sold<input type="radio" name="herdStatus" value="Sold">
            Butchered<input type="radio" name="herdStatus" value="Butchered">
            Dead<input type="radio" name="herdStatus" value="Dead">
        </div>
        <br><br>
        <button type="submit">Submit</button>
        </form>
</section>
<div id="sideContent">
    <form action="" method="get">
        <button>Add Cow</button><br>
        <button>Add Bull</button><br>
        <button>Add Calf</button><br>
    </form>
</div>
<%@include file="../../../pub/html/footer.html" %>

