<?php

	class Admin_model extends CI_Model {
		public function __construct () {
			$this->load->database();
		}
		public function verify_user () {
			$nombre = $this->input->post('name');
			$pass = $this->input->post('password');
			$query = $this->db->get_where ('user', array ('name'=>$nombre));
			$hash = $query->row_array()['password'];
			return password_verify($pass, $hash);
		}
		
		public function new_user () {
			$nombre = $this->input->post('name');
			$pass = $this->input->post('password');
			$data = array ('name'=>$nombre, 'password'=>password_hash($pass, PASSWORD_DEFAULT));
			return $this->db->insert('user', $data);
		}
		
		public function nuevas_comisiones ($comisiones) {
			$contador = 0;
			
			$this->db->empty_table('comision');
			$this->db->empty_table('carrera');
			$this->db->empty_table('materia');
			foreach ($comisiones as $data) {
				$insert_query = $this->db->insert_string('materia', array("descripcion"=>$data['materia']));
				$insert_query = str_replace('INSERT INTO','INSERT IGNORE INTO',$insert_query);
				$this->db->query($insert_query);
				$insert_query = $this->db->insert_string('carrera', array("descripcion"=>$data['carrera']));
				$insert_query = str_replace('INSERT INTO','INSERT IGNORE INTO',$insert_query);
				$this->db->query($insert_query);
				
				$this->db->select('codigo');
				$codigoCarrera = $this->db->get_where ('carrera', array ('descripcion'=>$data['carrera']))->row_array()['codigo'];
				$this->db->select('codigo');
				$codigoMateria = $this->db->get_where ('materia', array ('descripcion'=>$data['materia']))->row_array()['codigo'];
				

				if ($this->db->insert('comision', array ("codigoMateria"=>$codigoMateria, "codigoCarrera"=>$codigoCarrera, "turno"=>$data['turno'], "comision"=>$data['comision'], "horaInicio"=>$data['horaInicio'], "horaFin"=>$data['horaFin'], "dia"=>$data['dia'], "aula"=>$data['aula'])))
					$contador++;
			} 
			return $contador;
		}		
		
	}

?>
