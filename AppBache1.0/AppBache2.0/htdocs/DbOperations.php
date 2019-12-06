<?php
	class DbOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';
			$db = new DbConnect();
			$this -> con = $db -> connect();
		}

		public function createUser( $Correo, $Nombre, $Apellido, $Password){
			$stmt = $this -> con -> prepare
			("INSERT INTO `user` (`Correo`, `Nombre`, `Apellido`, `Password`, `user_type`) VALUES (?,?,?,?, 'Ciudadano');");
			$stmt -> bind_param( "ssss", $Correo, $Nombre, $Apellido, $Password );
			
			if( $stmt -> execute() ){
				return true;
			}else{
				return false;
			}
		}
	}	
?>