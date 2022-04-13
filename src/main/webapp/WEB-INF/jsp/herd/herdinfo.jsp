<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="../../../pub/html/header.html" %>--%>
<jsp:include page="../includes/header.jsp"/>
<section id="mainContent">
    <h1>Hello</h1>

    <h1>HERE</h1>

    <p>${cow}</p>

    <form action="/herd/submitAnimal" method="get">
        <label for="animalType">Animal Type</label>
        <div id="animalType">
            Cow<input type="radio" name="animalType" value="cow">
            Bull<input type="radio" name="animalType" value="bull">
            Calf<input type="radio" name="animalType" value="calf">
        </div>

        <%--        <select>--%>
        <%--            <option>Cow</option>--%>
        <%--            <option>Bull</option>--%>
        <%--            <option>Calf</option>--%>
        <%--        </select>--%>
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

        <%--These next two can be null values for cows and bulls--%>
        <%--        <label for="dob">Animal ID #2</label>--%>
        <%--        <input type="date" name="dob" id="dob"><br>--%>
        <%--        <label for="dod">Animal ID #2</label>--%>
        <%--        <input type="date" name="dod" id="dod"><br>--%>
        <br><br>
        <button type="submit">Submit</button>
    </form>
</section>
<div id="sideContent">
    <%--    <button>Add Animal</button>--%>

</div>


<jsp:include page="../includes/footer.jsp"/>
<%--<%@include file="../../../pub/html/footer.html" %>--%>
