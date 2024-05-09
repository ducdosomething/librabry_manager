<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 5/9/2024
  Time: 1:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.student_manager.model.Student" %>
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

<div class="container">
    <form class="mx-auto" method="post">
        <div class="text-center"><h1>Edit student</h1></div>
        <div>
            <c:if test="${students != null}">
                <input type="hidden" name="id" value="<c:out value='${students.id}' />"/>
            </c:if>
        </div>
        <div>
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<c:out value='${students.name}' />">
        </div>
        <div>
            <label for="email" class="form-label">Email</label>
            <input type="text" class="form-control" id="email" name="email" value="<c:out value='${students.email}' />">
        </div>
        <div>
            <label for="dob" class="form-label">Bob</label>
            <input type="date" class="form-control" id="dob" name="dob" value="<c:out value='${students.dob}' />">
        </div>
        <div>
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" name="address" id="address" value="<c:out value='${students.address}' />"/>
        </div>
        <div>
            <label for="phoneNumber" class="form-label">Phone number</label>
            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" value="<c:out value='${students.phoneNumber}' />"/>
        </div>
        <div>
            <label for="classroom" class="form-label">Classroom</label>
            <select class="form-control" id="classroom" name="classroom">
                <c:forEach items="${classrooms}" var="cl">
                    <option value="${cl.classId}"
                            <c:if test="${cl.classId == students.classroom.classId}">
                                selected
                            </c:if>
                    >${cl.className}</option>
                </c:forEach>
            </select>
        </div>
        <div class="button">
            <button type="submit" class="btn btn-primary mt-4" value="Update student">Update student</button>
            <button type="button" class="btn btn-dark mt-4" onclick="goToStudentList()">Back to student list</button>
        </div>

        <div class="show-message">
            <p>
                <c:if test='${requestScope["message"] != null}'>
                    <span class="message">${requestScope["message"]}</span>
                </c:if>
            </p>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
    function goToStudentList() {
        window.location.href = "/students";
    }
</script>
</body>
</html>
