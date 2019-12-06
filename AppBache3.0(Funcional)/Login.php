<?php

	require_once 'DbOperations.php';
	$response = array();	

	if ( $_SERVER['REQUEST_METHOD'] == 'POST' ){
		if (
			isset( $_POST['Correo'] ) and
			   isset( $_POST['Password'] )
		   ){
			$db = new DbOperations();
			if( $db -> userLogin( $_POST['Correo'], $_POST['Password'] ) ){
				$response['error'] = false;
				$response['message'] = "User successfully login.";
				$response['email'] = $_POST['Correo'];

			}else{
				$response['error'] = true;
				$response['message'] = "Invalid email or password.";
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