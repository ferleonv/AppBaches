<?php

	require_once 'DbOperations.php';
	$response = array();	

	if ( $_SERVER['REQUEST_METHOD'] == 'POST' ){
		if (
			isset( $_POST['Correo'] ) and
			 isset( $_POST['Nombre'] ) and
			  isset( $_POST['Apellido'] ) and
			   isset( $_POST['Password'] ) 
		   ){
			//operate the data futher
			$db = new DbOperations();
			if( $db -> createUser(
				$_POST['Correo'],
				$_POST['Nombre'],
				$_POST['Apellido'],
				$_POST['Password']
			    )
			){
				$response['error'] = false;
				$response['message'] = "User successfully registered.";
			}else{
				$response['error'] = true;
				$response['message'] = "Some error occurred, please try again.";
			}
			
		}
		else{
			$response['error'] = true;
			$response['message'] = "Required fields are missing.";
		}
	}
	else{
		$response['error'] = true;
		$response['message'] = "Invalid request.";
	}

	echo json_encode($response);
?>