<?php
	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");
	$resultado = $conexion->query ("select fecha, mensaje from undav_mensaje;");
	if (!$resultado)
		die ("Error.");
	else {
		while (($fila = $resultado->fetch_array (MYSQLI_NUM)) != null)
			$lista[] = array("fecha"=> utf8_encode($fila[0]), "mensaje"=> utf8_encode($fila[1]));
		print json_encode ($lista);
	}
?>
