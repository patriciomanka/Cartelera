<?php
	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	$carrera = utf8_decode($_POST["carrera"]);
	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");
	$resultado = $conexion->query ("select distinct descripcion from undav_comision,undav_materia where codigoMateria = codigo and codigoCarrera = (select codigo from undav_carrera where descripcion='".$carrera."');");
	if (!$resultado)
		die ("Error.");
	else {
		while (($fila = $resultado->fetch_array (MYSQLI_NUM)) != null)
			$lista[] = array("materia" => utf8_encode($fila[0]));
		print json_encode ($lista);
	}
?>
