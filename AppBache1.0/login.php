<?php
    $dbHost = "localhost";
    $dbUser = "root";
    $dbPassword = "";
    $db = "appbache";
    $con = mysqli_connect( $dbHost, $dbUser, $dbPassword, $db );

    $username = $_POST["usuario"];
    $password = $_POST["password"];

    $statement = mysqli_prepare ($con, "SELECT * FROM user WHERE Nombre = ?  AND Password = ?");
    mysqli_stmt_store_result ( $statement, "ss", $username, $password );
    mysqli_stmt_bind_result ( $statement, $idusuario, $nombre, $apellido, $correo, $password);

    $response = array();
    $statement ["success"] = false;

    while (mysqli_stmt_fetch($statement)){
        $response ["success"] = true;
        $response ["nombre"] = $nombre;
        $response ["apellido"] = $apellido;
        $response ["password"] = $correo;
        $response ["correo"] = $password;
    }

    echo json_encode ($response);

?>