<?php
	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	$materia = utf8_decode($_POST["materia"]);
	$carrera = utf8_decode($_POST["carrera"]);
	$turno = utf8_decode($_POST["turno"]);
        
	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");
	$resultado = $conexion->query ("select dia,horaInicio,horaFin,aula,comision from undav_comision where codigoMateria=(select codigo from undav_materia where descripcion='".$materia."') and codigoCarrera=(select codigo from undav_carrera where descripcion='".$carrera."')  and turno='".$turno."';");
	if (!$resultado)
		die ("Error.");
	else {
	while (($fila = $resultado->fetch_array (MYSQLI_NUM)) != null)
		$lista[] = array("dia1" => utf8_encode($fila[0]),"hi1" => utf8_encode($fila[1]),"hf1" => utf8_encode($fila[2]),"aula" => utf8_encode($fila[3]),"com" => utf8_encode ($fila[4]));
	print json_encode ($lista);
	}

?>
