<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 5/9/2024
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="my-container">
    <h1>Tour detail</h1>
    <form action="" method="post" class="text-center">
        <table border="1" class="table">
            <tr>
                <td style="width: 70%">ID</td>
                <td style="width: 30%;" value=""><c:out value ='${listdelete.id}'/></td>
            </tr>
            <tr>
                <td>NAME</td>
                <td value=""><c:out value ='${listdelete.name}'/></td>
            </tr>
            <tr>
                <td>EMAIL</td>
                <td value=""><c:out value ='${listdelete.email}'/></td>
            </tr>
            <tr>
                <td>DOB</td>
                <td value=""><c:out value ='${listdelete.dob}'/></td>
            </tr>
            <tr>
                <td>ADDRESS</td>
                <td value=""><c:out value ='${listdelete.address}'/></td>
            </tr>
            <tr>
                <td>PHONE NUMBER</td>
                <td value=""><c:out value ='${listdelete.phoneNumber}'/></td>
            </tr>
            <tr>
                <td>CLASSROOM</td>
                <td value=""><c:out value ='${listdelete.classroom.className}'/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="btn btn-danger" >DELETE</button>
                </td>
            </tr>
        </table>
    </form>
    <button class="btn btn-secondary" onclick="goToStudentList()">BACK</button>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
    function goToStudentList() {
        window.location.href = "/students";
    }
</script>
</body>
</html>
