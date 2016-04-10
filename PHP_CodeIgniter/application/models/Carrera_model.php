<?php

	class Carrera_model extends CI_Model {
		public function __construct () {
			$this->load->database();
		}
		public function get_carreras () {
			$this->db->select('descripcion');
			$query = $this->db->get ('carrera');
			return $query->result_array();
		}
		
		
		public function get_materias ($carrera) {
			$this->db->select('descripcion');
			$this->db->where("materia.codigo in (select codigoMateria from comision where codigoCarrera=(select codigo from carrera where descripcion = '$carrera'))");
			$query = $this->db->get ('materia');
			return $query->result_array();
		}
		
		public function get_comision ($carrera, $materia, $turno) {
			$this->db->select('dia,horaInicio,horaFin,aula,comision');
			$this->db->where("codigoCarrera=(select codigo from carrera where descripcion = '$carrera')");
			$this->db->where("codigoMateria=(select codigo from materia where descripcion = '$materia')");
			$this->db->where("turno", $turno);			
			$query = $this->db->get ('comision');
			return $query->result_array();
		}
	}

?>
