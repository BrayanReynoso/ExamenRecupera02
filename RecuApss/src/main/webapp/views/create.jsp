<%--
  Created by IntelliJ IDEA.
  User: Brayan
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrar</title>
    <jsp:include page="/templates/head.jsp"/>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="row">
                    <div class="card-header">
                        Crear Nuevo Registro
                    </div>
                </div>
                <div class="card-body">
                    <form action="add-user" method="post">
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col-3">
                                    <label for="nombre" class="form-label">Name</label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre">
                                </div>
                                <div class="col-3">
                                    <label for="apellidos" class="form-label">Apellidos</label>
                                    <input type="text" class="form-control" name="apellidos" id="apellidos">
                                </div>
                                <div class="col-3">
                                    <label for="birthday" class="form-label">Birthday</label>
                                    <input type="date" class="form-control" name="birthday" id="birthday" placeholder="DD/MM/YYYYY">
                                </div>
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" name="email" id="email">
                                </div>
                                <div class="col-3">
                                    <label for="phone" class="form-label">Phone</label>
                                    <input type="text" class="form-control" name="phone" id="phone">
                                </div>
                                <div class="col-3">
                                    <label for="username" class="form-label">Username</label>
                                    <input type="text" class="form-control" name="username" id="username">
                                </div>
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-outline-success">Registrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/templates/footer.jsp"/>
</body>
</html>
