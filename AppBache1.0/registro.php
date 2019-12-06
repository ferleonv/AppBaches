<?php
	$dbHost = "localhost";
	$dbUser = "root";
	$dbPass = "";
	$db = "appbache";
	$con = mysqli_connect( $dbHost, $dbUser, $dbPass, $db );

	$nombre = $_POST["nombre"];
	$apellido = $_POST["apellido"];
	$correo = $_POST["correo"];
	$password = $_POST["password"];

	$statement = mysqli_prepare($con, "INSERT INTO user (Nombre, Apellido, Correo, Password) VALUES (?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "ssis", $nombre, $apellido, $correo, $password );
	mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;

	echo json_encode($response);

?>