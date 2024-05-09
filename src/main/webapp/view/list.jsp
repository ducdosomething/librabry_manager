<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 5/7/2024
  Time: 3:26 PM
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
    <title>Student Manager</title>
</head>
<body>

    <section id="table">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 mb-3">
                <div class="card">
                    <div class="card-header">
                        <span><i class="bi bi-table me-2"></i></span> Student List
                        <button onclick="window.location.href='/students?action=create'" class="btn btn-primary">Create new tour</button>
                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="example" class="table table-striped data-table" style="width: 100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>DateOfBirth</th>
                                    <th>Address</th>
                                    <th>PhoneNumber</th>
                                    <th>Classroom</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="student" items="${listStudent}">
                                    <tr>
                                        <td><c:out value="${student.id}"/></td>
                                        <td><c:out value="${student.name}"/></td>
                                        <td><c:out value="${student.email}"/></td>
                                        <td><c:out value="${student.dob}"/></td>
                                        <td><c:out value="${student.address}"/></td>
                                        <td><c:out value="${student.phoneNumber}"/></td>
                                        <td><c:out value="${student.classroom.className}"/></td>
                                        <td>
                                            <button type="button" class="btn btn-info" onclick="window.location.href='/students?action=edit&id=${student.getId()}'">Edit</button>
                                            <button type="button" class="btn btn-danger" onclick="window.location.href='/students?action=delete&id=${student.getId()}'">Delete</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>DateOfBirth</th>
                                    <th>Address</th>
                                    <th>PhoneNumber</th>
                                    <th>Classroom</th>
                                    <th>Action</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
