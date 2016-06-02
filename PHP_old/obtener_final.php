<?php

	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	$materia = $_POST["materia"];
	$carrera = $_POST["carrera"];
	$llamado = $_POST["llamado"];

	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");
	$resultado = $conexion->query ("select fecha,hora,profesor from undav_final where codigoMateria=(select codigo from undav_materia where descripcion='".$materia."') and codigoCarrera=(select codigo from undav_carrera where descripcion='".$carrera."')  and llamado=".$llamado.";");
	if (!$resultado)
		die ("Error.");
	else {
		$fila = $resultado->fetch_assoc ();
		print json_encode ($fila);
	}

?>
