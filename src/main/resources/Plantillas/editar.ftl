<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Práctica 2 web</title>

</head>

<body>
<a class="nav-link" href="/">Inicio </a>
<div class="container">
    <header class="jumbotron my-4 text-center">
        <h1 class="display-3">Modificar</h1>
    </header>
    <hr>
    <form method="post" action="/Editado">

        <div class="form-group">
            <label for="matricula">Matricula</label>
            <input type="text" class="form-control" id="matricula" name="matricula" value='${Estudiante.matricula?string["0"]}' required readonly>
        </div>

        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control"  id="nombre" name="nombre" value="${Estudiante.nombre}" required>
        </div>

        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido"  value="${Estudiante.apellido}" required>
        </div>

        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input type="text" class="form-control" id="telefono" name="telefono" value="${Estudiante.telefono}" required>
        </div>
        <input type="submit" class="btn btn-primary" value="Guardar">

    </form>
</div>
</body>

</html>
