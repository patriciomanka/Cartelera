<?php
	function insertarDatos ($conexion, $ruta) {
		$lineas = file($ruta, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);

		$conexion->query ("delete from undav_comision");
                foreach ($lineas as $renglon) {
			$datos  = explode ("\t", $renglon);
			$carrera = $datos[0];
			$materia = $datos[1];
			$turno = $datos[2];
			$comision = $datos[3];
			$horaInicio = $datos[4];
			$horaFin = $datos[5];
			$dia = $datos[6];
			$aula = $datos[7];


			$conexion->query ("insert ignore undav_carrera(descripcion) values ('".$carrera."')");
			$conexion->query ("insert ignore undav_materia(descripcion) values ('".$materia."')");
			$esCorrecto = $conexion->query ("insert undav_comision(codigoCarrera,codigoMateria,turno,comision,horaInicio,horaFin,dia,aula) values ((select codigo from undav_carrera where descripcion='".$carrera."'),(select codigo from undav_materia where descripcion='".$materia."'),'".$turno."',".$comision.",".$horaInicio.",".$horaFin.",'".$dia."',".$aula.")");
			if ($esCorrecto)
				echo "La comision fue ingresada correctamente <br>";
			else
				echo "La comision no fue ingresada correctamente <br>";
		}
	}

	$ruta = "comisiones.txt";
	$conexion = new mysqli ("fdb13.biz.nf","1840451_kandaz","Kandaz2015","1840451_kandaz");
	if ($conexion->connect_errno)
		die ("Fallo la conexion a mysql.");

	if (!move_uploaded_file($_FILES['archivo']['tmp_name'], $ruta))
		echo "ERROR en la subida del archivo txt.";
	else
		insertarDatos ($conexion, $ruta);
?>
