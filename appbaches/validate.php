<?php
$username=$_POST['username'];
$password=$_POST['password'];

//conexion bd
$conexion=mysqli_connect("localhost", "root", "", "AppBaches");
$consulta="SELECT * FROM users WHERE username='$username' and password='$password' and is_CIDUE='1'";
$resultado=mysqli_query($conexion, $consulta);

$filas=mysqli_num_rows($resultado);

if ($filas > 0){
    header("location: dashboard.html");
}else {
    echo "Error en los datos ingresados.";
}
mysqli_free_result($resultado);
mysqli_close($conexion);

