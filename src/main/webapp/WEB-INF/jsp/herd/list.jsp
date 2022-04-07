<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../../pub/html/header.html" %>



<h1 style="text-align: center">Animal List Page</h1>

<table id="herdList">
    <tr>
        <th>Animal Id-1</th>
        <th>Animal Id-2</th>
        <th>Animal Type</th>
    </tr>
<c:forEach items="${cows}" var="cow">
    <tr>
        <td><a href="./herdinfo?cowId=${cow.animalId1}">${cow.animalId1}</a></td>
        <td>${cow.animalId2}</td>
        <td>${cow.animalType}</td>
    </tr>
</c:forEach>
</table>
<%@include file="../../../pub/html/footer.html" %>