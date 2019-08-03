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

<a class="nav-link" href="/">Inicio</a>


<div class="container">
    <header class="jumbotron my-4 text-center"></header>
    <hr>
    <div class="card">
        <div class="card-body">
            <ul class="list-group">
                <li class="list-group-item"><span class="font-weight-bold">Matricula: </span>${Estudiante.matricula?string["0"]}</span></li>
                <li class="list-group-item"><span class="font-weight-bold">Nombre: </span>${Estudiante.nombre}</li>
                <li class="list-group-item"><span class="font-weight-bold">Apellido: </span>${Estudiante.apellido}</li>
                <li class="list-group-item"><span class="font-weight-bold">Teléfono: </span>${Estudiante.telefono}</li>
            </ul>
        </div>
    </div>
</div>
</body>

</html>
