<?php
	class DbOperations{
		private $con;
		
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';
			$db = new DbConnect();
			$this -> con = $db -> connect();
		}

		public function createUser( $Correo, $Nombre, $Apellido, $Password){
			if( !( $this->userExist($Correo) ) ){
				$stmt = $this -> con -> prepare
				("INSERT INTO `user` (`Correo`, `Nombre`, `Apellido`, `Password`, `user_type`) VALUES (?,?,?,?, 'Ciudadano');");
				$stmt -> bind_param( "ssss", $Correo, $Nombre, $Apellido, $Password );
			
				if( $stmt -> execute() ){
					return 1;
				}else{
					return 2;
				}
			}else{
				return 0;
			}
		}

		public function userLogin($Correo, $Password){
			$stmt = ($this -> con) -> prepare ("SELECT * FROM user WHERE Correo=? AND Password=?");
			$stmt -> bind_param("ss", $Correo, $Password);
			$stmt -> execute();
			$stmt -> store_result();
			return ( $stmt -> num_rows > 0 );
		}

		private function userExist($Correo){
			$stmt = ( $this -> con ) -> prepare ("SELECT * FROM user WHERE email = ?");
			$stmt -> bind_param("s", $Correo);
			$stmt -> execute();
			$stmt -> store_result();
			return ( $stmt-> num_rows > 0 );
		}
	}	
?>