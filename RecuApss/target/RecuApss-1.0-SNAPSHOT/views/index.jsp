<%--
  Created by IntelliJ IDEA.
  User: Brayan
  Date: 26/08/2022
  Time: 12:29 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Persona</title>
    <jsp:include page="/templates/head.jsp"/>

</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <br>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-6">
                            Registros
                        </div>
                        <div class="col-6 text-end">
                            <a href="create-user" class="btn btn-outline-success">Registrar</a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>#</td>
                            <td>Name</td>
                            <td>Surname</td>
                            <td>Birthday</td>
                            <td>Email</td>
                            <td>Username</td>
                            <td>Password</td>
                            <td>Acciones</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="persona" items="${personas}" varStatus="status">
                            <tr>
                                <td>
                                    <c:out value="${status.count}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.nombre}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.apellidos}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.birthday}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.email}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.username}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${persona.password}"></c:out>
                                </td>
                                <td>
                                    <form action="eliminar" method="post">
                                        <input type="hidden" value="${persona.id}" name="id"/>
                                        <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/templates/footer.jsp"/>
</body>
</html>
