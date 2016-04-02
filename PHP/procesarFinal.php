<?php
	function insertarDatos ($conexion, $ruta) {
		$lineas = file($ruta, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

		foreach ($lineas as $renglon) {
			$datos  = explode ("\t", $renglon);
			$carrera = $datos[0];
			$materia = $datos[1];
			$llamado = $datos[2];
			$fecha = $datos[3];
			$hora = $datos[4];
			if (count ($datos) > 5)
				$profesor = "'".$datos[5]."'";
			else
				$profesor = "null";

			$esCorrecto = $conexion->query ("insert undav_final(codigoCarrera,codigoMateria,fecha,hora,llamado,profesor) values ((select codigo from undav_carrera where descripcion='".$carrera."'),(select codigo from undav_materia where descripcion='".$materia."'),'".$fecha."','".$hora."',".$llamado.",".$profesor.") ON DUPLICATE KEY UPDATE fecha='".$fecha."',hora='".$hora."',profesor=".$profesor);
			if ($esCorrecto)
				echo "El final fue ingresado correctamente <br>";
			else
				echo "El final no fue ingresado correctamente <br>";
		}
	}

	$ruta = "finales.txt";
	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");

	if (!move_uploaded_file($_FILES['archivo']['tmp_name'], $ruta))
		echo "ERROR en la subida del archivo txt."
	else
		insertarDatos ($conexion, $ruta);
?>
